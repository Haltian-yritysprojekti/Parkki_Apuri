const mysql = require('mysql')
const connection = mysql.createPool({
    host:'localhost',
    user:'apuri',
    password:'apuri',
    database:'parkkiapuri'
})
module.exports = connection