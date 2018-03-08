//dependencies
var express = require('express');
var router = express.Router();

//get models:
var Status = require('../models/status');

//routes
Status.methods(['get', 'post', 'put', 'delete']);
Status.register(router, '/status');

//return router
module.exports = router;


