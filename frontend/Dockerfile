FROM node:20.11.1-alpine3.19 as prod-builder

WORKDIR /app

RUN npm install -g vite

COPY . .

RUN npm install --force

EXPOSE 5173

CMD ["npm", "run", "dev"]
