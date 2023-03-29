const express = require('express')
const router = express.Router()
const parkit = require('../model/parkit')

//Rest API functions for front-ends
//Get Parking garages and number of free spaces
router.get('/',function(request,response){
    parkit.get(function(err,result){
        if(err){
            response.json(err)
        }else{
            response.json(result)
        }
    })
})
//Get parking slot statuses of parking garage
router.get('/:id?',function(request,response){
    parkit.getbyid(request.params.id,function(err,result){
        if(err){
            response.json(err)
        }else{
            var res = JSON.parse(JSON.stringify(result))
            
            for (let i = 0; i < result.length; i++) {
                if(result[i].etaisyys<500){
                    res[i].vapaa=false
                }else if(res[i].varattu==1){
                    res[i].vapaa=false
                }else{
                    res[i].vapaa=true
                }
            }
            response.json(res)
        }
    })
})


module.exports = router;