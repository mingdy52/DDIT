var MySql = require('sync-mysql');

var connection = new MySql({
	host: 'localhost',
	user: 'root',
	password: 'python',
	database: 'python',
	port: 3305
});

const result = connection.query('SELECT * from emp');
console.log("result",result)


