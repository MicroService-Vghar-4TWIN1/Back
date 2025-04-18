FROM node:20-alpine
EXPOSE 3000
COPY package*.json … 
RUN npm install --omit=dev
COPY . .
CMD  ["node","server.js"] 