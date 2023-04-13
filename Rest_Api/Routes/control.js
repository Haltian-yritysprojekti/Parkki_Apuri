const express = require('express')
const router = express.Router()
const control = require('../model/control')

//Parking garage Rest API database controls
//Get all parking garages
router.get('/garage',function(req,res){
    control.getP(function(err,result){
        if(err){
            res.json(err)
        }else{
            res.json(result)
        }
    })
})
//Get parking garage by name
router.get('/garage/:name?',function(req,res){
    control.Pgetbyname(req.params.name,function(err,result){
        if(err){
            res.json(err)
        }else{
            res.json(result)
        }
    })
})
//Add parking garage
router.post('/garage',function(req,res){
    control.Padd(req.body.name,function(err,result){
        if(err){
            res.json(err)
        }else{
            res.json(result)
        }
    })
})
//Update parking garage
router.put('/garage',function(req,res){
    control.Pupdate(function(err,result){
        if(err){
            res.json(err)
        }else{
            res.json(result)
        }
    })
})
//Delete parking garage
router.delete('/garage',function(req,res){
    control.Pdelete(function(err,result){
        if(err){
            res.json(err)
        }else{
            res.json(result)
        }
    })
})

//Parking sensor control
//Get sensors of parking garage
router.get('/sensor',function(req,res){

})
//Get sensor of parking garage
router.get('/sensor',function(req,res){

})
//Update sensor
router.put('/sensor',function(req,res){

})
//Add sensor to parking garage
router.post('/sensor',function(req,res){

})
//Delete sensor from parking garage
router.delete('/sensor',function(req,res){

})
//Get reserved spots
router.get('/reserve',function(req,res){
    control.Vget(function(err,result){
        if(err){
            console.log(err)
        }else{
            res.json(result)
        }
    })
})
//Add reserved spot
router.post('/reserve',function(req,res){
    control.Vadd(req.body,function(err,result){
        if(err){
            console.log(err)
        }else{
            res.json(result)
        }
    })
})
//Update reserved spot
router.put('/reserve',function(req,res){
    control.Vupdate(req.body,function(err,result){
        if(err){
            console.log(err)
        }else{
            res.json(result)
        }
    })
})
//Delete reserved spot
router.delete('/reserve',function(req,res){
    control.Vdelete(req.body,function(err,result){
        if(err){
            console.log(err)
        }else{
            res.json(result)
        }
    })
})

module.exports = router