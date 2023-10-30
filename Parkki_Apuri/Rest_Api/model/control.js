const db =require('../database')

//Database models for Rest API control
const control={
    //Models for parking garages
    //Get parking garages
    getP:function(callback){
        return db.query('select * from ParkkiTalo',callback)
    },
    //Get parking garage by name
    Pgetbyname:function(name,callback){
        db.query('select * from ParkkiTalo where sijainti=?',[name],callback)
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
        return db.query('delete from ParkkiTalo where sijainti=?',[name],callback)
    },
    //Models for parking garage sensors
    //Get parking garage sensors
    Sget:function(id,callback){
        return db.query('select * from Parkit where ParkkiTalo_id=?',[id],callback)
    },
    //Get parking garage sensor
    Sgetbyid:function(data,callback){
        return db.query('select * from Parkit where ParkkiTalo_id=? and sensor=?',[data.id,data.sensor],callback)
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
    },
    //Get all reserved spots from Varaukset
    Vget:function(callback){
        return db.query('select * from Varaukset',callback)
    },
    //Add reserve parking slot
    Vadd:function(data,callback){
        return db.query('insert into Varaukset values(?,0)',[data.id],callback)
    },
    //Update Varaukset
    Vupdate:function(data,callback){
        return db.query('update Varaukset set count=? where id=?',[data.count,data.id],callback)
    },
    //Delete reserve from Varaukset
    Vdelete:function(data,callback){
        return db.query('delete from Varaukset where id=?',[data.id],callback)
    }
}
module.exports = control