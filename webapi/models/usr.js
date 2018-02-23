//dependencies
var restful = require('node-restful');
var mongoose = restful.mongoose;

//schema
var usrSchema = new mongoose.Schema({
	usrname: String,
	usrpass: String
});

//return models
module.export = restful.model('tblstatus', usrSchema);