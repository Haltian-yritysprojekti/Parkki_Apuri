const fs = require('fs')
const debug = require('debug')('mqtt')
const mqtt = require('mqtt')
const key = fs.readFileSync('./sales-cloudext-prfi00parking/sales-cloudext-prfi00parking.key')
const cert = fs.readFileSync('./sales-cloudext-prfi00parking/sales-cloudext-prfi00parking.pem')
const parkit = require('./model/parkit')
const { error } = require('console')

//Setting MQTT options for connection
const options = {
    host:'a39cwxnxny8cvy.iot.eu-west-1.amazonaws.com',
    port:'8883',
    protocol:'mqtt',
    cert:cert,
    key:key,
    rejectUnauthorized:false
}

//Making MQTT connection to desired url
const client = mqtt.connect('mqtt://a39cwxnxny8cvy.iot.eu-west-1.amazonaws.com',options)

debug('mqtt trying connection to mqtt://a39cwxnxny8cvy.iot.eu-west-1.amazonaws.com')

//Debug if something wrong with connection
client.on('error',(...arg)=>{
    debug(arg)
})
//On connection subcribing to wanted topic
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
//Handling message and changing data to database on specific sensor 
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
        parkit.updatedist(res.tsmTuid,res.dist)
    }
})
