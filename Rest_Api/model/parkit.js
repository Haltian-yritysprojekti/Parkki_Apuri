const db = require('../database')

//Database models for front-end

const parkit={
    //Get count of free spaces and name of parking garages
    get:function(callback){
        return db.query('select sijainti,etaisyys,varattu from ParkkiTalo join Parkit on ParkkiTalo.id=Parkit.ParkkiTalo_id',callback)
    },
    //Get parking garages parking slots data
    getbyid:function(id,callback){
        return db.query('select *from Parkit where ParkkiTalo_id=?',[id],callback)
    },
    //Adding reservation to parking slot in parking garage
    addvaraus:function(id,name,callback){
        return db.query('update Parkit set varattu = 1 where idParkit=? and ParkkiTalo_id=?',[id,name],callback)
    },
    //Freeing reservation to parking slot in parking garage
    freevaraus:function(id,name,callback){
        return db.query('update Parkit set varattu = 0 where idParkit=? and ParkkiTalo_id=?',[id,name],callback)
    },
    //Rest API updating parking slots distance status from sensor data
    updatedist:function(id,dist,callback){
        return db.query('update Parkit set etaisyys = ? where sensor=?',[dist,id],callback)
    }
}

module.exports = parkit