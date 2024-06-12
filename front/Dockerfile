
# first step
FROM node:18-alpine AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build --prod

# Serve Application using Nginx Server
FROM nginx:alpine
COPY --from=build /app/dist/front/ /usr/share/nginx/html
EXPOSE 80