var MySql = require('sync-mysql');

var connection = new MySql({
	host: 'localhost',
	user: 'root',
	password: 'python',
	database: 'python',
	port: 3305
});

var e_id = '3';

var sql = `
	delete from emp 
	where
		e_id ='${e_id}'
`;

const result = connection.query(sql);
console.log("result",result.affectedRows)


