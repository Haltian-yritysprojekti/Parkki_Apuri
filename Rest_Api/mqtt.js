const fs = require('fs')
const mqtt = require('mqtt')
const key = fs.readFileSync('./sales-cloudext-prfi00parking/sales-cloudext-prfi00parking.key')
const cert = fs.readFileSync('./sales-cloudext-prfi00parking/sales-cloudext-prfi00parking.pem')
const parkit = require('./model/parkit')

const options = {
    Port:8883,
    cert:cert,
    key:key,
    Password:'none',
    topic:'cloudext/json/pr/fi/prfi00parking/#'
}

const client = mqtt.connect('mqtt://a39cwxnxny8cvy.iot.eu-west-1.amazonaws.com',options)

client.on('connect',()=>{
    console.log('connected')
    client.subscribe(options.topic)
    console.log(client.connected)
})

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