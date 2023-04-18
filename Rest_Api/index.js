const express = require('express');
const path = require('path');
const cors = require('cors')
const bodyparser = require('body-parser');
const sensorRouter = require('./Routes/sensor');
const controlRouter = require('./Routes/control')
const userRouter = require('./Routes/user')
const mqtt = require('./mqtt')


const app = express();
app.use(bodyparser.json());
app.use(express.urlencoded({extended: false}));
app.use(express.static(path.join(__dirname, 'public')));
app.use(express.json());
app.use(cors())

app.use('/', sensorRouter);
app.use('/user',userRouter)
app.use('/con', controlRouter)


module.exports = app;