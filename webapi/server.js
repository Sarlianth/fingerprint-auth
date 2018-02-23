//dependencies
var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser');

//connect to mongoDB
mongoose.connect('mongodb://sarlianth:project123!@ds245238.mlab.com:45238/fingerprint_auth');

//express
var app = express();
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

//routes
app.use('/api', require('./routes/api'));

//start on xx port
app.listen(1000);
console.log('Server running on port 1000');