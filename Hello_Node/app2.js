var bodyParser = require('body-parser');
var express = require('express');
var app = express();

app.use(bodyParser.json());	
app.use(bodyParser.urlencoded({ extended : false }));	

app.set('view engine','ejs'); // 1
app.use(express.static(__dirname + '/public'));

app.get('/', function (req, res) {
  res.send('Hello World!');
});


app.post('/emp.ajax', function (req, res) {
  res.json({'e_id':'1'});
});

app.get('/hello', function (req, res) {
  var a = "홍길동";
  var b = ["홍길동","전우치","이순신"];
  var c = [
    {'e_id':'1','e_name':'1','sex':'1','addr':'1'},
    {'e_id':'2','e_name':'2','sex':'2','addr':'2'},
  ];
  res.render('hello',{a:a,b:b,c:c});
});

app.get('/para', function (req, res) {
  var a = req.param('a');
  res.send('para:' + a);
});

app.post('/post', function (req, res) {
  var body = req.body;
  var a = body.a;
  res.send('post:'+a);
});

app.listen(3000, function () {
  console.log('Listening on port 3000!');
});
