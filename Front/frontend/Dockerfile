<<<<<<< HEAD
# build stage
FROM node:lts-alpine as build-stage
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build
 
# production stage
FROM nginx:stable-alpine as production-stage
# RUN rm -rf /etc/nginx/conf.d/default.conf
# COPY --from=build-stage /app/nginx/default.conf /etc/nginx/conf.d/default.conf
RUN rm -rf /usr/share/nginx/html/*
COPY --from=build-stage /app/dist /usr/share/nginx/html
EXPOSE 80
=======
FROM nginx:stable-alpine

WORKDIR /app

RUN mkdir ./build

ADD ./build ./build

RUN rm /etc/nginx/conf.d/default.conf

COPY ./nginx.conf /etc/nginx/conf.d

EXPOSE 8080

>>>>>>> d32bd5318690c698d6d16b5569794a703eccd8c3
CMD ["nginx", "-g", "daemon off;"]