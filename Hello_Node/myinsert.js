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
var e_name = '1';
var sex = '1';
var addr = '1';

var sql = `
	INSERT INTO emp 
		(e_id,e_name,sex,addr) 
	VALUES 
		('${e_id}','${e_name}','${sex}','${addr}')
`;

console.log(sql);

connection.query(sql, function (error, result) {
	console.log("-----------------------------------");
	console.log(error);
	console.log("-----------------------------------");
	console.log(result.affectedRows);

});

connection.end();
