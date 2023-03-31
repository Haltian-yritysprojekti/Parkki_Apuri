const { json } = require('body-parser')
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
            var res = JSON.parse(JSON.stringify(result))
            var a = res.reduce(function(r, o){
                var k = o.sijainti;
                if(o.varattu != 0 || o.etaisyys<500){
                    r[k] = (r[k])? (r[k]+0) : 0;
                }else{
                    r[k] = (r[k])? (r[k]+1) : 1;
                }
                return r;
            }, {});
            res=[]
            for ([key,value] of Object.entries(a)){
                if(a.hasOwnProperty(key)){
                    res.push({
                        sijainti:key,
                        vapaa:value
                    })
                }
            }
            response.json(res)
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
router.get('/hello',function(request,response){
    response.send('hello world')
})


module.exports = router;