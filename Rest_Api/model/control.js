const db =require('../database')

//Database models for Rest API control
const control={
    //Models for parking garages
    //Get parking garages
    Pget:function(callback){
        return db.query('select * from ParkkiTalo')
    },
    //Get parking garage by name
    Pgetbyname:function(name,callback){
        var a = db.query('select id from ParkkiTalo where sijainti=?',[name],callback)
        console.log(a)
    },
    //Add parking garage
    Padd:function(name,callback){
        return db.query('insert into ParkkiTalo values("null",?)',[name],callback)
    },
    //Update parking garage
    Pupdate:function(data,callback){
        return db.query('update ParkkiTalo set sijainti=? where id=?',[data.name,data.id],callback)
    },
    //Delete parking garage
    Pdelete:function(name,callback){
        return db.query('delete from ParkkiTalo where sijainti=?'[name],callback)
    },
    //Models for parking garage sensors
    //Get parking garage sensors
    Sget:function(id,callback){
        return db.query('select * from Parkit where ParkkiTalo_id=?',[id],callback)
    },
    //Get parking garage sensor
    Sgetbyid:function(id,sensor,callback){
        return db.query('select * from Parkit where ParkkiTalo_id=? and sensor=?',[id,sensor],callback)
    },
    //Add sensor to parking garage
    Sadd:function(data,callback){
        return db.query('insert into Parkit values(null,?,?,?,?)',[data.dist,data.var,data.sensor,data.id],callback)
    },
    //Update sensor of parking garage
    Supdate:function(data,callback){
        return db.query('update Parkit set etaisyys=?,varattu=? where sensor=?',[data.dist,data.var,data.sensor],callback)
    },
    //Delete sensor from parking garage
    Sdelete:function(data,callback){
        return db.query('delete from Parkit where ParkkiTalo_id=? and sensor=?',[data.id,data.sensor],callback)
    }
}