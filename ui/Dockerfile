FROM node:14-alpine as builder

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

FROM node:14-alpine

WORKDIR /dist

COPY --from=builder /dist .

EXPOSE 3000

CMD ["npm", "run", "serve"]
