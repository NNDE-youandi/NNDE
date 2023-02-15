const app = require("express")();
const server = require("http").createServer(app);
const io = require("socket.io")(server);
const roomInfo = {};
const idNick = {};
const numberOfMemberSurvey = {};
const KeyWordIdx = {};
const Keyword = {};
const roomTime = {};
const surveyQuiz = {};
const keywordTeammember = {};
const balancePage = {};

//setting cors
app.all("/*", function (req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  next();
});

//connection event handler
io.on("connection", function (socket) {
  console.log("연결된 소켓: " + socket.id);

  // exit room
  socket.on("exitRoom", () => {
    const myRoom = [...socket.rooms][1];
    if (roomInfo[myRoom][2].length === 1) {
      delete roomInfo[myRoom];
    } else {
      for (let i = 0; i < roomInfo[myRoom][2].length; i++) {
        if (roomInfo[myRoom][2] === idNick[socket.id]) {
          roomInfo[myRoom][2].slice(i, 1);
          i--;
        }
      }
    }
    socket.leave(myRoom);
  });
  // RoomView

  socket.on("submitPin", (data) => {
    const pin = data.pin;
    if (findRooms(pin) && roomInfo[pin][1] > roomInfo[pin][2].length) {
      socket.join(pin);
      roomInfo[pin][2].push(idNick[socket.id]);
      console.log(roomInfo);
      io.to(socket.id).emit("movePinRoom", {
        modeName: roomInfo[pin][0],
      });
    } else if (findRooms(pin) && roomInfo[pin][1] <= roomInfo[pin][2].length) {
      io.to(socket.id).emit("fullRoom");
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
    roomInfo[roomNumber].push(roomMember);
    console.log(roomInfo);
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
  socket.on("getUserNick", (data) => {
    idNick[socket.id] = data;
    console.log(idNick[socket.id]);
  });
  //survey waiting
  socket.on("addSurveyMember", () => {
    numberOfMemberSurvey[[...socket.rooms][1]].push(idNick[socket.id]);
  });
  socket.on("getNumberOfMemberSurvey", () => {
    io.to([...socket.rooms][1]).emit("sendNumberOfMemberSurvey", {
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
    io.to(parseInt(roomNumber)).emit("resCheckParticipant", {
      roomNumber: roomNumber,
      limitMember: roomInfo[roomNumber][1],
      participant: [...roomInfo[roomNumber][2]],
    });
  });
  // socket.on("callCheckParticipant", () => {
  //   const roomNumber = [...socket.rooms][1];
  //   const participant = [null, null]
  //   if (roomInfo[roomNumber][2]) {
  //     participant = roomInfo[roomNumber][2];
  //   }
  //   io.to(parseInt(roomNumber)).emit("resCheckParticipant", {
  //     roomNumber: roomNumber,
  //     limitMember: roomInfo[roomNumber][1],
  //     participant: [...participant],
  //   });
  // });
  //iceBreakingStart
  socket.on("getKeyword", (data) => {
    Keyword[[...socket.rooms][1]] = data;
  });

  //KeyWord
  socket.on("callKeyword", () => {
    io.to([...socket.rooms][1]).emit(
      "resKeyword",
      Keyword[[...socket.rooms][1]]
    );
  });
  socket.on("callPlusIndex", () => {
    KeyWordIdx[[...socket.rooms][1]] = KeyWordIdx[[...socket.rooms][1]] + 1;
  });
  socket.on("callTeamMember", () => {
    io.to([...socket.rooms][1]).emit(
      "resTeamMember",
      roomInfo[[...socket.rooms][1]][2],
      roomInfo[[...socket.rooms][1]][1],
      KeyWordIdx[[...socket.rooms][1]]
    );
  });
  socket.on("callStep1CountRoutine", () => {
    KeyWordIdx[[...socket.rooms][1]] += 1;
    io.to([...socket.rooms][1]).emit("resStep1CountRoutine", "Step1Count");
  });
  socket.on("callStep1Outro", () => {
    io.to([...socket.rooms][1]).emit("resStep1CountRoutine", "Step1Outro");
  });
  //surveyQuiz
  // surveydata 저장하기
  socket.on("getSurveyQuizData", (nick, answer, survey) => {
    surveyQuiz[[...socket.rooms][1]] = [nick, answer, survey];
    console.log(surveyQuiz[[...socket.rooms][1]]);
  });

  // surveydata 보내주기
  socket.on("callSurveyQuizData", () => {
    io.to([...socket.rooms][1]).emit(
      "resSurveyQuizData",
      surveyQuiz[[...socket.rooms][1]]
    );
  });
  //surveyquiz 정답 보내주기
  socket.on("callSurveyQuizResult", () => {
    io.to([...socket.rooms][1]).emit(
      "resSurveytQuizResult",
      surveyQuiz[[...socket.rooms][1]][0]
    );
  });
  //인덱스 값 변경해주기
  socket.on("callSurveyIndex", () => {
    io.to([...socket.rooms][1]).emit(
      "resSurveyIndex",
      KeyWordIdx[[...socket.rooms][1]]
    );
  });
  socket.on("plusSurveyIndex", (data) => {
    KeyWordIdx[[...socket.rooms][1]] = data;
  });
  //SurveyWaiting
  socket.on("callIceBreakingStart", () => {
    io.to([...socket.rooms][1]).emit("resIceBreakingStart", "IceBreakingStart");
  });

  //IceBreakingStart
  socket.on("callStep1Count", () => {
    KeyWordIdx[[...socket.rooms][1]] = 0;
    console.log(KeyWordIdx);
    io.to([...socket.rooms][1]).emit("resStep1Count", "Step1Count");
  });

  //Step1Outro
  socket.on("callStep2Start", () => {
    KeyWordIdx[[...socket.rooms][1]] = 0;
    keywordTeammember[[...socket.rooms][1]] = []
    io.to([...socket.rooms][1]).emit("resStep2Start", "Step2Start");
  });

  //Step2Start
  socket.on("callStep2Count", () => {
    io.to([...socket.rooms][1]).emit("resStep2Count", "Step2Count");
  });
  //step2QuizTostep2Outro
  socket.on("callStep2Outro", () => {
    io.to([...socket.rooms][1]).emit("resStep2Outro", "Step2Outro");
  });

  //sendroomMode
  socket.on("callRoomMode", () => {
    io.to([...socket.rooms][1]).emit(
      "resRoomType",
      roomInfo[[...socket.rooms][1]][0]
    );
  });

  //iceToStep4Start
  socket.on("callStep4Start", () => {
    io.to([...socket.rooms][1]).emit("resStep4Start", "Step4Start");
  });

  //step4StartToLiar
  socket.on("callLiarGame", () => {
    io.to([...socket.rooms][1]).emit("resLiarGame", "LiarThemeList");
  });
  //LiarToIce
  socket.on("callIceLastPage", () => {
    io.to([...socket.rooms][1]).emit("resIceLastPage", "IceEnd");
  });

  // EndIce
  socket.on("callendPageTeamMember", () => {
    console.log(roomInfo[[...socket.rooms][1]][2]);
    io.to([...socket.rooms][1]).emit(
      "resendPageTeamMember",
      roomInfo[[...socket.rooms][1]][2]
    );
  });
  socket.on("startTime", () => {
    roomTime[[...socket.rooms][1]] = Date.now();
  });
  socket.on("endTime", () => {
    let duringTime = 111;
    if (roomTime[[...socket.rooms][1]] > 100000000) {
      duringTime = parseInt(
        (Date.now() - roomTime[[...socket.rooms][1]]) / 1000
      );
      roomTime[[...socket.rooms][1]] = duringTime;
    } else {
      duringTime = parseInt(roomTime[[...socket.rooms][1]]);
    }
    socket.emit("sendTime", duringTime);
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
  socket.on("getMyNick", () => {
    io.to(socket.id).emit("sendMyNick", idNick[socket.id]);
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
  socket.on("startBalance", () => {
    balancePage[[...socket.rooms][1]] = 1;
    io.to([...socket.rooms][1]).emit("startBalanceGame", 1);
  });
  socket.on("requestNextPage", () => {
    balancePage[[...socket.rooms][1]] += 1;
    io.to([...socket.rooms][1]).emit(
      "sendNextPage",
      balancePage[[...socket.rooms][1]]
    );
  });
  socket.on("requestPrevPage", () => {
    balancePage[[...socket.rooms][1]] -= 1;
    io.to([...socket.rooms][1]).emit(
      "sendPrevPage",
      balancePage[[...socket.rooms][1]]
    );
  });
  socket.on("goBalance", () => {
    io.to([...socket.rooms][1]).emit("moveBalancePage", "Step3Start");
  });
  socket.on("goBalanceStage", () => {
    io.to([...socket.rooms][1]).emit("moveBalanceStagePage", "Balance");
  });
  socket.on("getBalanceData", (ateam, bteam) => {
    io.to([...socket.rooms][1]).emit("sendBalanceData", ateam, bteam);
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
  socket.on("getTeamMember", () => {
    let randomMember = getRandomName(roomInfo[[...socket.rooms][1]][2], keywordTeammember[[...socket.rooms][1]])
    io.to([...socket.rooms][1]).emit(
      "sendTeamMember",
      roomInfo[[...socket.rooms][1]][2],
      randomMember
    );
    keywordTeammember[[...socket.rooms][1]].push(randomMember)

    console.log("랜덤 이름 :",randomMember)
    console.log("keywordname :", keywordTeammember[[...socket.rooms][1]])
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
  if (everyRoom.includes(randomRoomNumber)) {
    generateRandomCode(6);
  } else {
    return parseInt(randomRoomNumber);
  }
}

function getRandomName(allName, usedName) {
  console.log("함수 내 팀멤버:", allName )
  console.log("함수 내 빈 칸 :", usedName)
  let randomName = allName[Math.floor(Math.random() * allName.length)]

  if (usedName.includes(randomName)) {
    getRandomName(allName, usedName)
  } else {
    return randomName
  }
}