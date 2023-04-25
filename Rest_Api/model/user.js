const db = require('../database')

const user={
    //Add user
    addUser:function(data,callback){
        return db.query('insert into User values(?,?,?,null)',[data.email,data.salasana,data.rekisteri],callback)
    },
    //Update user
    updateUser:function(data,callback){
        return db.query('update User set email=?, rekisteri=? where userid=?',[data.email,data.rekisteri,data.userid],callback)
    },
    //Get users
    getUser:function(callback){
        return db.query('select * from User',callback)
    },
    //Delete user
    deleteUser:function(data,callback){
        return db.query('delete from User where userid=?',[data.userid],callback)
    },
    //Login user
    userLogin:function(data,callback){
        return db.query('select * from User where email=?',[data.email],callback)
    }
}

module.exports = user