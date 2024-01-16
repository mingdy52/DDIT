const mysql = require('mysql');  // mysql 모듈 로드
const conn = mysql.createConnection({
        host: 'localhost',
		port: '3305',
        user: 'root',
        password: 'phthon',
        database: 'python'
    });
 
var connection = mysql.createConnection(conn); // DB 커넥션 생성
connection.connect();   // DB 접속
 
// var testQuery = "INSERT INTO `members` (`username`,`password`) VALUES ('test','test');";
 
// connection.query(testQuery, function (err, results, fields) { // testQuery 실행
    // if (err) {
        // console.log(err);
    // }
    // console.log(results);
// });
 
testQuery = "SELECT * FROM emp";
 
connection.query(testQuery, function (err, results, fields) { // testQuery 실행
    if (err) {
        console.log(err);
    }
    console.log(err, results, fields);
});
 
 
connection.end(); // DB 접속 종료
