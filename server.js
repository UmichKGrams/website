// requires modules
var express = require('express');
var port = 3001;
var serverUrl = "127.0.0.1";

// allows use of Express
var app = express();

// states that files used are in "public" folder
app.use(express.static(__dirname + '/public'));

console.log("Starting web server at " + serverUrl + ":" + port);
app.listen(port);