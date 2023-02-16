# 🐱 🐶 🐔 너나들이 - ICE BREAKING

![타이틀](/uploads/b3f0fd5df51ba85cd8870bec56120366/타이틀.png)


## 너나들이 링크(모바일 화면) : [https://i8c209.p.ssafy.io/](https://i8c209.p.ssafy.io/)
## 소개 영상 보기 : [UCC 링크](![너나들이](/uploads/ac4e68010f341d294c2b827f23ae968f/너나들이.mp4))

## 🌊 프로젝트 진행 기간
2023.01.2(월) ~ 2023.02.17(금)
SSAFY 8기 2학기 공통프로젝트 - 너나들이

</br>

## 😄 너나들이 - 개요
새로운 만남을 가질 때 분위기를 어떻게 풀지 고민하신 적이 있나요 ? 
대화 주제에 대해 고민하신 적이 있나요 ? 
이야기를 어떻게 이어가야할지 고민하신 적이 있나요 ?

‘너나들이’는 이러한 어색함을 타파하고자 탄생한 아이스브레이킹 기반 반응형 웹 서비스 입니다.

‘너나들이’와 함께라면, 더 이상 이야기를 어떻게 이어가야할지 고민하시지 않아도 됩니다.


</br>

## 😆 주요 기능
---
- ### 아이스브레이킹
    - 새로운 만남을 가질 때 분위기를 어떻게 풀지 고민하신 적이 있나요? 
    - 당장 방을 만들어 새로운 사람들과 어색함을 풀어보세요!
    - 퀴즈를 위한 간단한 설문조사 후 4단계로 구성된 아이스브레이킹 모드를 즐겨보세요!
    - step1 : 나를 소개해봐
    - step2 : 나를 찾아봐
    - step3 : 밸런스 게임
    - step4 : 라이어 게임
    - 팀원들과 너나들이를 즐기며 분위기를 따뜻하게 만들어봐요!
    <br/>
- ### 밸런스 게임
    - 고르기 어려운 두 가지의 선택지에 대해서 친구들과 얘기해보아요!
    <br/>
- ### 폭탄돌리기 게임
    - 폭탄을 돌리고 싶은 시간만큼 시간을 맞춰주세요! 벌칙도 미리 정하면 좋겠죠?
    - 째깍째깍! ⏰ 질문을 답하면서 폭탄을 다음 사람에게 패스해요!
    - 펑! 💣 시간이 지날때까지 폭탄을 가지고 있는 사람에게 벌칙 부여!!
    <br/>
- ### 라이어 게임
    - 누군가가 다른 단어를 가지고 있지만 같은 단어를 가지고 있다고 거짓말 하고 있어요!
    - 라이어를 찾아주세요!
    - 단, 라이어에게 단어에 대한 힌트를 너무 주면 안 돼요!
</br>

## ✔ 주요 기술
---

**Backend - Spring**
- IntelliJ IDEA 2022.3.1 (Ultimate Edition) : 17.0.5+1-b653.23 amd64
- Spring : 5.3.25
- SpringBoot : 2.7.8
- mysql : 8.0.31
- redis : 3.0.504
- Spring Data JPA
- Spring Validation
- Spring Web

**Socket**
- Node.js : 14.21.2
- socket.io : 4.5.4
- express : 4.18.2

**Frontend**
- Visual Studio Code IDE : 1.74.2
- Vue.js : 3.2.47
- Node.js : 14.21.2
- Vuex
- Sock.js-Client
- socket.io : 4.5.4
- vue-socket.io : 3.0.10
- vuex-persistedstate : 4.1.0
- node-sass : 8.0.0
- sass-loader : 13.2.0
- axios : 1.2.6
- email-validator : 2.0.4

**CI/CD**
- AWS EC2 Ubuntu 20.04.5 LTS
- NGINX
- SSL

## ✔ 프로젝트 파일 구조
---
### Back
```
└─com
    └─ssafy
        └─youandi
            ├─config
            │  ├─advice
            │  │  └─exception
            │  ├─jwt
            │  ├─redis
            │  ├─security
            │  └─social
            ├─controller
            │  └─game
            ├─dto
            │  ├─kakao
            │  ├─request
            │  └─response
            ├─entity
            │  ├─game
            │  │  ├─balancegame
            │  │  ├─bombgame
            │  │  ├─liargame
            │  │  └─oxquiz
            │  ├─keyword
            │  ├─mypageinfo
            │  ├─randomnick
            │  ├─redis
            │  └─user
            │      ├─answer
            │      ├─fileinfo
            │      └─survey
            ├─repository
            │  └─game
            └─service
                └─game
```
### Socket
```
├─ node_modules
├─ package.json
└─ server.js
```
### Front
```
└─com
    └─ssafy
        └─youandi
            ├─config
            │  ├─advice
            │  │  └─exception
            │  ├─jwt
            │  ├─redis
            │  ├─security
            │  └─social
            ├─controller
            │  └─game
            ├─dto
            │  ├─kakao
            │  ├─request
            │  └─response
            ├─entity
            │  ├─game
            │  │  ├─balancegame
            │  │  ├─bombgame
            │  │  ├─liargame
            │  │  └─oxquiz
            │  ├─keyword
            │  ├─mypageinfo
            │  ├─randomnick
            │  ├─redis
            │  └─user
            │      ├─answer
            │      ├─fileinfo
            │      └─survey
            ├─repository
            │  └─game
            └─service
                └─game
```


## ✔ 협업 툴
---
- Git
- Notion
- JIRA
- MatterMost
- Webex
- Discord

## ✔ 협업 환경
---
- Gitlab
  - 코드의 버전을 관리
  - 이슈 발행, 해결을 위한 토론
  - MR시, 팀원이 코드리뷰를 진행하고 피드백 게시
- JIRA
  - 매주 목표량을 설정하여 Sprint 진행
  - 업무의 할당량을 정하여 Story Point를 설정하고, In-Progress -> Done 순으로 작업  
- 회의
  - Gether Town 아침회의 진행, 전날 목표 달성량과 당일 할 업무 브리핑
  - 각자 위치에서 건네야 할 말이 생기면 팀원의 위치로 이동하여 전달
  - 빠른 소통과 신속한 대응이 가능하다.
- Notion
  - 회의가 있을때마다 회의록을 기록하여 보관
  - 회의가 길어지지 않도록 다음날 제시할 안건을 미리 기록
  - 기술확보 시, 다른 팀원들도 추후 따라할 수 있도록 보기 쉽게 작업 순서대로 정리
  - 컨벤션 정리
  - 간트차트 관리
  - 스토리보드, 시퀀스다이어그램, 기능명세서 등 모두가 공유해야 하는 문서 관리


## ✔ 팀원 역할 분배
---
![팀원_소개](/uploads/1deaa117c61acf76ce723f404cdf1efe/팀원_소개.png)


