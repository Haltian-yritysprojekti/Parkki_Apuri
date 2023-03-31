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
    control.Pupdate(req.body,function(err,result){
        if(err){
            res.json(err)
        }else{
            res.json(result)
        }
    })
})
//Delete parking garage
router.delete('/garage',function(req,res){
    control.Pdelete(req.body.name,function(err,result){
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
    control.Sget(req.body.id,function(err,result){
        if(err){
            res.json(err)
        }else{
            res.json(result)
        }
    })
})
//Get sensor of parking garage
router.get('/sensor/id',function(req,res){
    control.Sgetbyid(req.body,function(err,result){
        if(err){
            res.json(err)
        }else{
            res.json(result)
        }
    })
})
//Update sensor
router.put('/sensor',function(req,res){
    control.Supdate(req.body,function(err,result){
        if(err){
            res.json(err)
        }else{
            res.json(result)
        }
    })
})
//Add sensor to parking garage
router.post('/sensor',function(req,res){
    control.Sadd(req.body,function(err,result){
        if(err){
            res.json(err)
        }else{
            res.json(result)
        }
    })
})
//Delete sensor from parking garage
router.delete('/sensor',function(req,res){
    control.Sdelete(req.body,function(err,result){
        if(err){
            res.json(err)
        }else{
            res.json(result)
        }
    })
})

module.exports = router