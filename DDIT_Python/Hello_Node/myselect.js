var mysql = require('mysql');
var connection = mysql.createConnection({
	host: 'localhost',
	user: 'root',
	password: 'python',
	database: 'python',
	port: 3305
});

connection.connect();

connection.query('SELECT * from emp', function (error, results, fields) {

	console.log("-----------------------------------");
	console.log(error);
	console.log("-----------------------------------");
	console.log(results);
	console.log("-----------------------------------");
	console.log(fields);
});

connection.end();
