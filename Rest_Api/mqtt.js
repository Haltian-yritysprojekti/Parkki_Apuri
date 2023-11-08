const fs = require('fs')
const debug = require('debug')('mqtt')
const mqtt = require('mqtt')
const key = fs.readFileSync('./sales-cloudext-prfi00parking/sales-cloudext-prfi00parking.key')
const cert = fs.readFileSync('./sales-cloudext-prfi00parking/sales-cloudext-prfi00parking.pem')
const { error } = require('console')
const db = require('./database')

//Options of the MQTT connection
const options = {
    host:'a39cwxnxny8cvy.iot.eu-west-1.amazonaws.com',
    port:'8883',
    protocol:'mqtt',
    cert:cert,
    key:key,
    rejectUnauthorized:false
}

//connecting with MQTT to MQTT broker
const client = mqtt.connect('mqtts://a39cwxnxny8cvy.iot.eu-west-1.amazonaws.com',options)

debug('mqtt trying connection to mqtt://a39cwxnxny8cvy.iot.eu-west-1.amazonaws.com')


client.on('error',(...arg)=>{
    debug(arg)
})

//On connection subscribe to topic with sensor data
client.on('connect',()=>{
    console.log('connected')
    debug(client.connected)
    client.subscribe('cloudext/json/pr/fi/prfi00parking/#',function(err){
        if(!err){
            debug('subscribed')
        }else{
            debug('subscribe failed')
        }
    })
    
})

//When incoming message change to JSON and send to database
client.on('message',(message,topic,packet)=>{
    var a = Buffer.from(packet.payload.buffer)
    var b = a.toJSON()
    var c=[]
    var d = ""
    for(x of b.data)
    {
        c.push(String.fromCharCode(x))
    }
    c.forEach(element => {
        if(element=="{"){
            d=element
        }else if(element==","){
            d+="\n"+element
        }else if(element=="}"){
            d+="\n"+element
        }else{
            d+=element
        }
    });
    c=[]
    c.push(d)
    var res = JSON.parse(c)
    console.log('message received')
    console.log(res)
    if(res.tsmId==17200){
        updateDist(res.tsmTuid,res.dist)
    }
})

//Checking if connection disconnected and try reconnect
function checkconnection(){
    if(client.connected){
        debug(' client is connected')
    }else{
        client.reconnect()
        if(client.connected){
            debug('client reconnected')
        }else{
            debug('reconnection failed')
        }
    }
}

//Updating database with new sensor data
function updateDist(id,dist){
    db.query('update Parkit set etaisyys = ? where sensor=?',[dist,id])
}

const checkTime = 1000*60*1
setInterval(checkconnection,checkTime)