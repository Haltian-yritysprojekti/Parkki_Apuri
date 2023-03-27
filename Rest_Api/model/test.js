const db = require('../database')

const test={
    get:function(callback){
        return db.query('select * from test',callback)
    }
}

module.exports = test