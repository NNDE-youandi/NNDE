var app = require("express")();
var server = require("http").createServer(app);
var io = require("socket.io")(server);
// 전역변수 roomInfo에 필요한 정보는 다같이 보면서 합치기
// 방장, limitMember를 배열의 길이로 join할때 비교
// join이나 emit등 roomNumber는 int로, 딕셔너리의 키로는 string로 변환
var roomInfo = {};
let limitMember = 0;
var teamHosts = {};

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
        pin: pin,
      });
    } else {
      io.to(socket.id).emit("noRoom");
    }
  });
  socket.on("makeRoom", (data) => {
    const roomNumber = generateRandomCode(6);

    teamHosts[roomNumber] = socket.id;
    limitMember = data.limitMember;
    roomInfo[roomNumber] = [];
    socket.join(roomNumber);
    roomInfo[roomNumber].push(socket.id);
  });
  socket.on("getId", () => {
    let isHost = false;
    if (socket.id === teamHosts[[...socket.rooms][1]]) {
      isHost = true;
    }
    io.to(socket.id).emit("receiveId", isHost);
  });

  // WaitingRoomView
  socket.on("callCheckParticipant", () => {
    const roomNumber = [...socket.rooms][1];
    io.to(parseInt(roomNumber)).emit("checkParticipant", {
      roomNumber: roomNumber,
      limitMember: limitMember,
      participant: [...roomInfo[roomNumber]],
    });
  });
  socket.on("goBoom", (roomNumber) => {
    io.to(parseInt(roomNumber)).emit("moveBoomPage", "boomgame");
  });
  // BoomGameView
  socket.on("handleBoom", () => {
    io.to([...socket.rooms][1]).emit("moveBoom");
  });
  socket.on("getBoomNumber", () => {
    const socketRoom = [...socket.rooms][1];
    const numberOfClients = io.sockets.adapter.rooms.get(socketRoom).size;
  });
  //LiarGameView
  function randomValueFromArray(array) {
    const random = Math.floor(Math.random() * array.length);
    return random;
  }
  socket.on("goLiar", () => {
    const socketRoom = [...socket.rooms][1];
    console.log(socketRoom);
    io.to([...socket.rooms][1]).emit("moveLiarPage", "LiarGame");
  });

  socket.on("pickRandom", () => {
    //시작 버튼 누르면 랜덤으로 한명 뽑게 됨.
    //LiarId 값에 한명 저장
    const socketRoom = [...socket.rooms][1];
    const setTeamMember = io.sockets.adapter.rooms.get(socketRoom);
    const teamMember = [];
    setTeamMember.forEach((element) => {
      teamMember.push(element);
    });
    const Liar = randomValueFromArray(teamMember);
    const liarData = [teamMember, Liar];
    io.to([...socket.rooms][1]).emit("pickLiar", liarData);
  });
  socket.on("requestId", () => {
    socket.emit("sendId", socket.id);
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
  }
  if (randomRoomNumber in everyRoom) {
    generateRandomCode(6);
  } else {
    return parseInt(randomRoomNumber);
  }
}
