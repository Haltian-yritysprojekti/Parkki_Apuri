const express = require('express')
const router = express.Router()


router.get('/sensor',function(request,response){
    response.json(test)
})

module.exports = router;