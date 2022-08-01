var DaoEmp = require('./dao_emp.js');

var de = new DaoEmp();
var list = de.myselect();
console.log(list);