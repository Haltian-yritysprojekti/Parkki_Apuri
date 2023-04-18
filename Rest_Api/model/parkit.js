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
    //Adding reservation to parking slot in parking garage
    addvaraus:function(data,callback){
        return db.query('update Parkit set varattu = 1, rekisteri=? where idParkit=?',[data.rekisteri,data.id],callback)
    },
    //Freeing reservation to parking slot in parking garage
    freevaraus:function(id,callback){
        return db.query('update Parkit set varattu = 0,rekisteri=null where idParkit=?',[id],callback)
    },
    updatedist:function(id,dist,callback){
        return db.query('update Parkit set etaisyys = ? where sensor=?',[dist,id],callback)
    },
    //Check parking reservation spaces
    check:function(callback){
        return db.query('select * from Parkit',callback)
    },
    //Get Varaukset
    varauksetGet:function(callback){
        return db.query('select * from Varaukset',callback)
    },
    //Get user reservation with register
    varausUser:function(data,callback){
        return db.query('select * from Parkit where rekisteri=?',[data.rekisteri],callback)
    },
    //Add reservation to Varaukset
    varauksetAdd:function(id,callback){
        return db.query('insert into Varaukset values(?,0)',[id],callback)
    },
    //Update reservation count in Varaukset
    varauksetUp:function(id,count,callback){
        return db.query('update Varaukset set count=? where id=?',[count,id],callback)
    },
    //Delete reservation from Varaukset
    varauksetDel:function(id,callback){
        return db.query('delete from Varaukset where id=?',[id],callback)
    }
}

module.exports = parkit