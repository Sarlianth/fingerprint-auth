//dependencies
var express = require('express');
var router = express.Router();

//models
var Usr = require('../models/usr');

//routes
Usr.methods = (['get', 'post', 'put', 'delete']);
Usr.register = (router, '/usr');

//return router
module.exports = router;