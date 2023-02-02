var app = require("express")();
var server = require("http").createServer(app);
var io = require("socket.io")(server);

var roomInfo = {};

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
    // socket.broadcast.emit("viewRoom", {
    //   roomNumber: data.roomNumber,
    // });
    roomInfo[data.roomNumber] = [];
    socket.join(data.roomNumber);
    roomInfo[data.roomNumber].push(socket.id);
  });
  socket.on("joinRoom", (data) => {
    socket.join(data.roomNumber);
    roomInfo[data.roomNumber].push(socket.id);
  });
  // WaitingRoomView
  socket.on("callCheckParticipant", () => {
    const roomNumber = [...socket.rooms][1]
    io.to(parseInt(roomNumber)).emit("checkParticipant", {
      "roomNumber": roomNumber,
      "participant": [...roomInfo[roomNumber]]}
    );
  });
  socket.on("goBoom", (roomNumber) => {
    io.to(parseInt(roomNumber)).emit("moveBoomPage", "boomgame");
  });
  // BoomGameView
  socket.on("handleBoom", () => {
    // 소켓의 방 이름 구하기
    io.to([...socket.rooms][1]).emit("moveBoom");
  });
  socket.on("getBoomNumber", () => {
    const socketRoom = [...socket.rooms][1];
    // room에 연결 돼 있는 client 수 > socket 3.x
    const numberOfClients = io.sockets.adapter.rooms.get(socketRoom).size;
  });
  //LiarGameView
  function randomValueFromArray(array) {
    const random = Math.floor(Math.random() * array.length);
    return random;
  }
  // 웅기 수정본
  socket.on("goLiar", (roomNumber) => {
    io.to(parseInt(roomNumber)).emit("moveLiarPage", "liargame");
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
    console.log(teamMember);
    io.to([...socket.rooms][1]).emit("pickLiar", liarData);
  });
  // 이 부분은 userdata를 vue에 저장하면 사라지게 됨.
  socket.on("requestId", () => {
    socket.emit("sendId", socket.id);
  });
});

server.listen(3001, function () {
  console.log("socket io server listening on port 3001");
});

// 현재 활동중인 방 목록 구하기
function findRooms(pin) {
  const rooms = io.sockets.adapter.rooms;
  for (let room of rooms) {
    if (room[0] === pin) {
      return true;
    }
  }
  return false;
}
