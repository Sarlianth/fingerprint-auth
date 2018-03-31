//dependencies
var restful = require('node-restful');
var mongoose = restful.mongoose;

var userSchema = new mongoose.Schema({
	name: String,
});

//return models:
module.exports = restful.model('tbluser', userSchema);