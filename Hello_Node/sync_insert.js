var MySql = require('sync-mysql');

var connection = new MySql({
	host: 'localhost',
	user: 'root',
	password: 'python',
	database: 'python',
	port: 3305
});

var e_id = '3';
var e_name = '3';
var sex = '3';
var addr = '3';

var sql = `
	INSERT INTO emp 
		(e_id,e_name,sex,addr) 
	VALUES 
		('${e_id}','${e_name}','${sex}','${addr}')
`;

const result = connection.query(sql);
console.log("result",result.affectedRows)


