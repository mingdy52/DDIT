var DaoEmp = require('./dao_emp.js');
var express = require('express');
var bodyParser = require('body-parser');
var cors = require('cors');
var app = express();
var de= new DaoEmp();


app.use(cors());
app.use(bodyParser.json());	
app.use(bodyParser.urlencoded({ extended : false }));	

app.set('view engine','ejs'); // 1
app.use(express.static(__dirname + '/public'));

app.get('/', function (req, res) {
  res.render('emp');
});


app.post('/emp.ajax', function (req, res) {
  res.json({'e_id':'1'});
});


app.post('/emp_list.ajax', function (req, res) {
  var mylist = de.myselects();
  res.json(mylist);
});

app.post('/emp_one.ajax', function (req, res) {
  var body = req.body;
  var e_id = body.e_id;
  var emp = de.myselect(e_id);
  res.json(emp);
});

app.post('/emp_add.ajax', function (req, res) {
  var body = req.body;
  var e_id = body.e_id;
  var e_name = body.e_name;
  var sex = body.sex;
  var addr = body.addr;

  var cnt = -1;
  try{
    cnt = de.myinsert(e_id, e_name, sex, addr);
  } catch (err){
    
  }

  res.json({'cnt':cnt});
});

app.post('/emp_mod.ajax', function (req, res) {
  var body = req.body;
  var e_id = body.e_id;
  var e_name = body.e_name;
  var sex = body.sex;
  var addr = body.addr;

  var cnt = -1;
  try{
    cnt = de.myupdate(e_id, e_name, sex, addr);
  } catch (err){
    
  }

  res.json({'cnt':cnt});
});

app.post('/emp_del.ajax', function (req, res) {
  var body = req.body;
  var e_id = body.e_id;

  var cnt = -1;
  try{
    cnt = de.mydelete(e_id);
  } catch (err){
    
  }

  res.json({'cnt':cnt});
});

app.listen(3000, function () {
  console.log('Listening on port 3000!');
});






