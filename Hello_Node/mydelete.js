var mysql = require('mysql');
var connection = mysql.createConnection({
	host: 'localhost',
	user: 'root',
	password: 'python',
	database: 'python',
	port: 3305
});

connection.connect();

var e_id = '5';


var sql = `
	delete from emp 
	where
		e_id ='${e_id}'
`;

console.log(sql);

connection.query(sql, function (error, result) {
	console.log("-----------------------------------");
	console.log(error);
	console.log("-----------------------------------");
	console.log(result.affectedRows);

});

connection.end();
