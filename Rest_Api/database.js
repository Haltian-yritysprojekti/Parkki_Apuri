const mysql = require('mysql')
const cert = require('./db.json')
const connection = mysql.createPool({
    host:'parkkiapuri.c8b4oczahqbe.eu-north-1.rds.amazonaws.com',
    user:cert.user,
    password:cert.password,
    database:'ParkkiApuri'
})
module.exports = connection