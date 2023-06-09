var app = require('../index');
var debug = require('debug')('server'),
    http = require('https');
const fs = require('fs')
const key = fs.readFileSync('./server/server.key')
const cert = fs.readFileSync('./server/server.cert')

options={
  key:key,
  cert:cert
}

//get port and store to express
var port = normalizePort(process.env.PORT || '4000');
app.set('port',port);

//create http server
var server = http.createServer(options,app);

//listen to port
server.listen(port);
server.on('error', onError);
server.on('listening', onListening);

//normalize port to a number, string or false
function normalizePort(val) {
    var port = parseInt(val, 10);
  
    if (isNaN(port)) {
      
      return val;
    }
  
    if (port >= 0) {
      
      return port;
    }
  
    return false;
  }

  //listen http server error
  function onError(error) {
    if (error.syscall !== 'listen') {
      throw error;
    }
  
    var bind = typeof port === 'string'
      ? 'Pipe ' + port
      : 'Port ' + port;
  
    switch (error.code) {
      case 'EACCES':
        console.error(bind + ' requires elevated privileges');
        process.exit(1);
        break;
      case 'EADDRINUSE':
        console.error(bind + ' is already in use');
        process.exit(1);
        break;
      default:
        throw error;
    }
  }

  //listen http server
  function onListening() {
    var addr = server.address();
    var bind = typeof addr === 'string'
      ? 'pipe ' + addr
      : 'port ' + addr.port;
    debug('Listening on ' + bind);
    console.log('server online')
  }
