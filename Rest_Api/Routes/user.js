const express = require('express')
const router = express.Router()
const user = require('../model/user')
const { end } = require('../database')

//Get users
router.get('/',function(request,response){
    user.getUser(function(err,result){
        if(err){
            console.log(err)
        }else{
            response.json(result)
        }
    })
})
//Sing up user
router.post('/',function(request,response){
    user.getUser(function(err,result){
        if(err){
            console.log(err)
        }else{
            var res = JSON.parse(JSON.stringify(result))
            for(let i=0; i<res.length ;i++){
                if(res[i].email==request.body.email){
                    response.send('Email Already in use')
                    return
                }
            }  
        }
        user.addUser(request.body,function(err,result){
            if(err){
                response.send('error')
            }else{
                response.send('successful')
            }
        })
    }) 
})
//Update user
router.put('/',function(request,response){
    user.updateUser(request.body,function(err,result){
        if(err){
            response.send('error')
            console.log(err)
        }else{
            response.send('successful')
        }
    })
})
//Delete user
router.delete('/',function(request,response){
    user.deleteUser(request.body,function(err,result){
        if(err){
            response.send('error')
        }else{
            response.send('successful')
        }
    })
})
//Login user
router.post('/login',function(request,response){
    user.userLogin(request.body,function(err,result){
        if(err){
            console.log(err)
        }else{
            var res = JSON.parse(JSON.stringify(result))
            if(res.length>0){
                if(request.body.salasana == result[0].salasana ){
                    response.json(result)
                }else{
                    response.send('Wrong password')
                }
            }else{
                response.send('No user found')
            }
        }
    })
})

module.exports = router