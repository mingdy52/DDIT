const mysql      = require('sync-mysql');

class DaoEmp{
	constructor() {
		this.conn = new mysql({
			host: 'localhost', 
			user: 'root',  
			password: 'python', 
			database: 'python',
			port:3305
		});
	}
	myselects(){
		let result = this.conn.query('SELECT * from emp');
		return result
	}

	myselect(e_id){
		let sql = 
		`SELECT e_id,e_name,sex,addr 
		from emp where e_id = '${e_id}'`;
		let result = this.conn.query(sql)
		return result[0]
	}

	myinsert(e_id,e_name,sex,addr){
		let sql = `
			INSERT INTO emp 
				(e_id,e_name,sex,addr) 
			VALUES
				('${e_id}','${e_name}','${sex}','${addr}')
		`;
		let result = this.conn.query(sql);
		return result.affectedRows;
	}
	myupdate(e_id,e_name,sex,addr){
		let sql = `
			update emp
			set
				e_name = '${e_name}',
				sex = '${sex}',
				addr = '${addr}'
			where
				e_id = '${e_id}'
		`;
		let result = this.conn.query(sql);
		return result.affectedRows;
	}
	mydelete(e_id){
		let sql = `
			DELETE FROM EMP
			WHERE
				e_id = '${e_id}'
		`;
		let result = this.conn.query(sql);
		return result.affectedRows;
	}

}

module.exports = DaoEmp;

