//dependencies

var express = require('express');
var mongoose = require('mongoose');
var bodyParser = require('body-parser');

//Connect to mongoDB :
mongoose.connect('mongodb://yourdbus:matreka7890@ds261078.mlab.com:61078/mydb');

//express
var app = express();
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());

//routes
app.use('/api', require('./routes/api'));

//start Server:
app.listen(1000);
console.log('Server is running on port 1000');