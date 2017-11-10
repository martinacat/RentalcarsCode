var express = require('express');
var app = express();
var fs = require("fs");

app.get('/cars', function (req, res) {
   fs.readFile( __dirname + "/" + "vehicles.json", 'utf8', function (err, data) {
       console.log( data );
       res.end( data );
   });
})

app.get('/car_specs', function (req, res) {
   fs.readFile( __dirname + "/" + "car_specs.txt", 'utf8', function (err, data) {
       console.log( data );
       res.end( data );
   });
})

app.get('/highest_rating', function (req, res) {
   fs.readFile( __dirname + "/" + "highest_rating.txt", 'utf8', function (err, data) {
       console.log( data );
       res.end( data );
   });
})

app.get('/highest_total_score', function (req, res) {
   fs.readFile( __dirname + "/" + "highest_total_score.txt", 'utf8', function (err, data) {
       console.log( data );
       res.end( data );
   });
})



var server = app.listen(8081, function () {

  var port = server.address().port

  console.log("Example app listening at http://127.0.0.1:%s", port)

})
