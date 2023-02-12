const app = require("express")();
const server = require("http").createServer(app);
const io = require("socket.io")(server);
// 전역변수 roomInfo에 필요한 정보는 다같이 보면서 합치기
// 방장, limitMember를 배열의 길이로 join할때 비교
// join이나 emit등 roomNumber는 int로, 딕셔너리의 키로는 string로 변환
const roomInfo = {};
const idNick = {};
const numberOfMemberSurvey = {}

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
      roomInfo[pin][2].push(idNick[socket.id]);
      io.to(socket.id).emit("movePinRoom", {
        modeName: roomInfo[pin][0],
      });
    } else {
      io.to(socket.id).emit("noRoom");
    }
  });
  socket.on("makeRoom", (data) => {
    const roomNumber = generateRandomCode(6);
    socket.join(roomNumber);
    roomInfo[roomNumber] = [];
    roomInfo[roomNumber].push(data.modeName);
    roomInfo[roomNumber].push(data.limitMember);
    const roomMember = [idNick[socket.id]];
    roomInfo[roomNumber].push(roomMember)
    console.log(roomInfo)
    numberOfMemberSurvey[roomNumber] = [];
  });
  socket.on("getIsHost", () => {
    let isHost = false;
    if (idNick[socket.id] === roomInfo[[...socket.rooms][1]][2][0]) {
      isHost = true;
    }
    io.to(socket.id).emit("sendIsHost", isHost);
  });

  // 소캣 아이디를 유저 닉네임으로.
  socket.on('getUserNick', (data) => {
    idNick[socket.id] = data
    console.log(idNick[socket.id])
  })
  //survey waiting
  socket.on("addSurveyMember", () => {
    numberOfMemberSurvey[[...socket.rooms][1]].push(idNick[socket.id]);
  });
  socket.on("getNumberOfMemberSurvey", () => {
    socket.emit("sendNumberOfMemberSurvey", {
      numberOfMemberSurvey: numberOfMemberSurvey[[...socket.rooms][1]].length,
      numberOfFullMember: roomInfo[[...socket.rooms][1]][2],
    });
  });

  // RoomWaiting
  socket.on("callMoveNextRoom", () => {
    
    io.to([...socket.rooms][1]).emit("resMoveNextRoom");
  });

  socket.on("callCheckParticipant", () => {
    const roomNumber = [...socket.rooms][1];
    console.log(roomInfo[roomNumber][1])
    io.to(parseInt(roomNumber)).emit("resCheckParticipant", {
      roomNumber: roomNumber,
      limitMember: roomInfo[roomNumber][1],
      participant: [...roomInfo[roomNumber][2]],
    });
  });
// BoomGameView
socket.on("callHandleBoom", (data) => {
  io.to([...socket.rooms][1]).emit("resHandleBoom", data);
});
socket.on("callPass", (data) => {
  io.to([...socket.rooms][1]).emit("resPass", data);
});
socket.on("getRoomClientsId", () => {
  const socketRoom = [...socket.rooms][1];
  const roomClients = [...roomInfo[socketRoom][2]];
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
    const setTeamMember = roomInfo[[...socket.rooms][1]][2];
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
    socket.emit("sendId", idNick[socket.id]);
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
  //servey
  socket.on("getTeamMember",() => {
    io.to([...socket.rooms][1]).emit("sendTeamMember", roomInfo[[...socket.rooms][1]][2], roomInfo[[...socket.rooms][1]][2][randomValueFromArray(roomInfo[[...socket.rooms][1]][2])])
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