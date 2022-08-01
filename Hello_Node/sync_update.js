var MySql = require('sync-mysql');

var connection = new MySql({
	host: 'localhost',
	user: 'root',
	password: 'python',
	database: 'python',
	port: 3305
});

var e_id = '3';
var e_name = '7';
var sex = '7';
var addr = '7';

var sql = `
    update emp
    set 
        e_name='${e_name}',
        sex='${sex}',
        addr='${addr}'
    where 
        e_id='${e_id}'
`;

const result = connection.query(sql);
console.log("result",result.affectedRows)


