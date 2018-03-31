//dependencies
var express = require('express');
var router = express.Router();

//get models:
var Status = require('../models/status');
var User = require('../models/user');

//routes
Status.methods(['get', 'post', 'put', 'delete']);
Status.register(router, '/status');

User.methods(['get', 'post', 'put', 'delete']);
User.register(router, '/user');

//return router
module.exports = router;


