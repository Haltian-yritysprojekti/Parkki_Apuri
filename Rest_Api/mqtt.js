const { json } = require('body-parser')
const mqtt = require('mqtt')
const options = {
    Port:1883,
    Password:'none',
    topic:'cloudext/json/pr/fi/office/XXXX04E2E84100933/#'
}

const client = mqtt.connect('mqtt://live-data.haltian.com',options)
var ResMessage={}

client.on('connect',()=>{
    console.log('connected')
    client.subscribe(options.topic)
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
    ResMessage=res
})

const mqttt={
get:function(){
    return ResMessage
}
}
module.exports = mqttt