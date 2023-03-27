const express = require('express')
const router = express.Router()
const mqtt = require('../mqtt')
const test = require('../model/test')
const { route } = require('..')


router.get('/sensor',function(request,response){
    response.json(mqtt.get())
})
router.get('/test',function(request,response){
    test.get(function(err,result){
        if(err){
            response.json(err)
        }else{
            response.json(result)
        }
    })
})


module.exports = router;