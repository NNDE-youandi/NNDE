# 도커로부터 node를 베이스 이미지로 가져오기
FROM node

# 컨테이너에 워킹 디렉토리를 만들어 app 소스코드를 구분하기
WORKDIR /usr/src/app

# npm install의 정상 수행을 위해, 그전에 컨테이너 내부에 패키지 모듈 명세 파일 복사
COPY package*.json ./

# 도커 서버가 수행할 커맨드 추가
RUN npm install

# package.json 외의 다른 파일들도 복사 (index.js가 없으면 CMD 명령어 수행 불가함)
# COPY를 두 부분으로 구분해둔 이유 : 효율적인 재빌드 목적
COPY ./ ./

EXPOSE 3001

# 컨테이너가 실행될 때 1번만 수행되는 시작 명령어 자리에 들어갈 커맨드
CMD [ "node", "server.js" ]