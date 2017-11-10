var express = require('express');
var app = express();
var fs = require("fs");

app.get('/listcars', function (req, res) {
   fs.readFile( __dirname + "/" + "vehicles.json", 'utf8', function (err, data) {
       console.log( data );
       res.end( data );
   });
})

var server = app.listen(8081, function () {

  var port = server.address().port

  console.log("Example app listening at http://127.0.0.1:%s", port)

})