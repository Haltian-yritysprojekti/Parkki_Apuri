const express = require('express')
const router = express.Router()
const parkit = require('../model/parkit')



router.get('/test',function(request,response){
    parkit.get(function(err,result){
        if(err){
            response.json(err)
        }else{
            response.json(result)
        }
    })
})
//network test
router.get('/hello',function(req,res){
    console.log('hello')
    res.send('hello')
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
//Reserve parking slot
router.put('/',function(request,response){
    parkit.getSlot(request.body.id,function(err,res){
        if(err){
            console.log('error happened')
        }else{
            if(res.length!=0){
            if(res[0].etaisyys<500 || res[0].varattu==1){
                console.log('Paikka käytössä tai varattu')
                response.status(404).send('Already in use')
            }else{
                parkit.addvaraus(request.body.id,function(err,result){
                    if(err){
                        response.json(err)
                    }else{
                        console.log(res[0].idParkit)
                        resv.push({id:res[0].idParkit,count:0})
                        response.send('succesfull')
                    }
                })
            }
        }else{
            console.log('no such slot')
            response.status(404).send('no such slot')
        }
        }
    })
})

//Free parking reservations when reserved space in use
function checkReservation(){
    parkit.check(function(err,result){
        if(err){
            console.log('Error in checking reservations')
        }else{
            var arr = JSON.parse(JSON.stringify(result))
            arr.forEach(e => {
                if(e.varattu==1 && e.etaisyys<500){
                    parkit.freevaraus(e.idParkit,function(err,res){
                        if(err){
                            console.log('Could not change park '+e.idParkit)
                        }else{
                            console.log("Park with id"+e.idParkit+" reservation freed")
                        }
                    })
                }
            });
        }
    })
    if(resv.length>0){
    resv.forEach(element => {
        if(element.count>2){
            parkit.freevaraus(element.id,function(err,res){
                if(err){
                    console.log('Could not change park '+element.id)
                }else{
                    resv.splice(resv.indexOf(element.id),1)
                    console.log(resv)
                    console.log("Park with id"+element.id+" reservation freed")
                }
            })
        }else{
            element.count+=1
        }
    });
}
}


const checkTime = 1000*60*1
setInterval(checkReservation, checkTime)
module.exports = router;