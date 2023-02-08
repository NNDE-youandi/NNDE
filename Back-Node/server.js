const app = require("express")();
const server = require("http").createServer(app);
const io = require("socket.io")(server);
// 전역변수 roomInfo에 필요한 정보는 다같이 보면서 합치기
// 방장, limitMember를 배열의 길이로 join할때 비교
// join이나 emit등 roomNumber는 int로, 딕셔너리의 키로는 string로 변환
const roomInfo = {};
const teamHosts = {};
const roomType = {};
let limitMember = 0;

//setting cors
app.all("/*", function (req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  next();
});

//connection event handler
io.on("connection", function (socket) {
  console.log("연결된 소켓: " + socket.id);

  // RoomView
  socket.on("submitPin", (data) => {
    const pin = data.pin;
    if (findRooms(pin)) {
      socket.join(pin);
      roomInfo[pin].push(socket.id);
      io.to(socket.id).emit("movePinRoom", {
        modeName: roomType[pin],
      });
    } else {
      io.to(socket.id).emit("noRoom");
    }
  });
  socket.on("makeRoom", (data) => {
    const roomNumber = generateRandomCode(6);
    roomInfo[roomNumber] = [];
    teamHosts[roomNumber] = socket.id;
    roomType[roomNumber] = data.modeName;
    limitMember = data.limitMember;
    socket.join(roomNumber);
    roomInfo[roomNumber].push(socket.id);
  });
  socket.on("getIsHost", () => {
    let isHost = false;
    if (socket.id === teamHosts[[...socket.rooms][1]]) {
      isHost = true;
    }
    io.to(socket.id).emit("sendIsHost", isHost);
  });
  // RoomWaiting
  socket.on("callMoveNextRoom", () => {
    io.to([...socket.rooms][1]).emit("resMoveNextRoom");
  });

  socket.on("callCheckParticipant", () => {
    const roomNumber = [...socket.rooms][1];
    io.to(parseInt(roomNumber)).emit("resCheckParticipant", {
      roomNumber: roomNumber,
      limitMember: limitMember,
      participant: [...roomInfo[roomNumber]],
    });
  });
  // BoomGameView
  socket.on("callHandleBoom", () => {
    io.to([...socket.rooms][1]).emit("resHandleBoom");
  });
  socket.on("getRoomClientsId", () => {
    const socketRoom = [...socket.rooms][1];
    const roomClients = io.sockets.adapter.rooms.get(socketRoom);
    socket.emit("sendRoomClientsId", {
      roomClients: [...roomClients],
    });
  });
  socket.on("callMoveBoomStage", (data) => {
    io.to([...socket.rooms][1]).emit("resMoveBoomStage", {
      boomTime: data.boomTime,
      url: "BoomStage",
    });
  });
  // LiarGameView
  function randomValueFromArray(array) {
    const random = Math.floor(Math.random() * array.length);
    return random;
  }
  // data는 liarSubject를 전달해줌. => LiarGameView에 subject를 전달
  socket.on("goLiarList", (liarSubject, liarWord) => {
    //router.push name값을 전달

    io.to([...socket.rooms][1]).emit("goLiarPage", "LiarStage");
    //sendLiarWord를 받으면 data(방장이 정한 주제)를 전달해줌.
    io.to([...socket.rooms][1]).emit("sendLiarWord", liarSubject, liarWord);
  });
  socket.on("sendLiarGame", (data) => {
    io.to([...socket.rooms][1]).emit("pickLiarSubject", data);
  });
  // 랜덤 번호를 보내줌.
  socket.on("pickRandom", () => {
    //시작 버튼 누르면 랜덤으로 한명 뽑게 됨.
    //LiarId 값에 한명 저장
    const setTeamMember = io.sockets.adapter.rooms.get([...socket.rooms][1]);
    const teamMember = {};
    const teamMemberToList = [];
    setTeamMember.forEach((element) => {
      teamMemberToList.push(element);
      teamMember[element] = 0;
    });

    const Liar = teamMemberToList[randomValueFromArray(teamMemberToList)];
    const liarData = [teamMember, Liar];
    io.to([...socket.rooms][1]).emit("pickLiar", liarData);
  });
  // socket id 값을 보내줌.
  socket.on("requestId", () => {
    socket.emit("sendId", socket.id);
  });

  socket.on("sendUserList", (data) => {
    const values = Object.values(data);
    let result = 0;
    for (let i = 0; i < Object.keys(data).length; i++) {
      result += values[i];
    }
    io.to([...socket.rooms][1]).emit("voteResult", data, result);
  });

  //goResult를 받은 이후, 라이어가 걸렸다면 if문
  // 라이어가 아닌 다른 사람이 걸렸다면 else 문을 실행.
  socket.on("goResult", (liarId, liarWord, votedUser) => {
    if (liarId === votedUser) {
      io.to([...socket.rooms][1]).emit("goResultPage", "LiarDetectSuccess");
      io.to([...socket.rooms][1]).emit("LiarIdData", liarId, liarWord);
    } else {
      io.to([...socket.rooms][1]).emit("goResultPage", "LiarDetectFail");
      io.to([...socket.rooms][1]).emit("LiarIdData", liarId, liarWord);
    }
  });

  socket.on("checkAnswer", (liarWord, liarAnswer, liarId) => {
    io.to([...socket.rooms][1]).emit("liarResult", "LiarResult");
    io.to([...socket.rooms][1]).emit(
      "liarLastPage",
      liarWord,
      liarAnswer,
      liarId
    );
  });
  // balanceGame
  var balancePage = 1;
  socket.on("startBalance", (data) => {
    io.to([...socket.rooms][1]).emit("startBalanceGame", data);
  });
  socket.on("requestNextPage", () => {
    balancePage += 1;
    io.to([...socket.rooms][1]).emit("sendNextPage", balancePage);
  });
  socket.on("requestPrevPage", () => {
    balancePage -= 1;
    io.to([...socket.rooms][1]).emit("sendPrevPage", balancePage);
  });
  socket.on("goBalance", () => {
    const socketRoom = [...socket.rooms][1];
    io.to([...socket.rooms][1]).emit("moveBalancePage", "Balancegame");
  });
  // survey
  socket.on("goKeywordIntroduce", () => {
    io.to([...socket.rooms][1]).emit("moveKeywordPage", "KeyWord");
  });
  // [subin] step2Start : 나를 맞춰봐
  socket.on("goStep2Start", () => {
    io.to([...socket.rooms][1]).emit("moveStep2Start", "Step2Start");
  });
});

server.listen(3001, function () {
  console.log("socket io server listening on port 3001");
});
// 핀 번호의 방이 존재하는지
function findRooms(pin) {
  const rooms = io.sockets.adapter.rooms;
  for (let room of rooms) {
    if (room[0] === pin) {
      return true;
    }
  }
  return false;
}

// 랜덤 핀번호
function generateRandomCode(numberLength) {
  const everyRoom = Object.keys(roomInfo);
  let randomRoomNumber = "";
  for (let i = 0; i < numberLength; i++) {
    randomRoomNumber += Math.floor(Math.random() * 10);
    if (i === 0 && randomRoomNumber === "0") {
      randomRoomNumber = "9";
    }
  }
  if (randomRoomNumber in everyRoom) {
    generateRandomCode(6);
  } else {
    return parseInt(randomRoomNumber);
  }
}
