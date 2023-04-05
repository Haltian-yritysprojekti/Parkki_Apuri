const db = require('../database')

const parkit={
    get:function(callback){
        return db.query('select count(varattu),sijainti from ParkkiTalo join Parkit on ParkkiTalo.id=Parkit.ParkkiTalo_id where varattu=0 group by sijainti',callback)
    },
    getbyid:function(id,callback){
        return db.query('select idParkit,etaisyys,varattu from Parkit join ParkkiTalo on Parkit.ParkkiTalo_id=ParkkiTalo.id where sijainti=?',[id],callback)
    },
    //Get parking slot info with id
    getSlot:function(id,callback){
        return db.query('select * from Parkit where idParkit=?',[id],callback)
    },
    addvaraus:function(id,name,callback){
        return db.query('update Parkit set varattu = 1 where idParkit=? and ParkkiTalo_id=?',[id,name],callback)
    },
    freevaraus:function(id,name,callback){
        return db.query('update Parkit set varattu = 0 where idParkit=? and ParkkiTalo_id=?',[id,name],callback)
    },
    updatedist:function(id,dist,callback){
        return db.query('update Parkit set etaisyys = ? where sensor=?',[dist,id],callback)
    }
}

module.exports = parkit