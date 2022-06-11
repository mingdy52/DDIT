from flask import Flask
import pymysql
from flask.templating import render_template
from flask.globals import request

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'Hello World'

@app.route('/para')
def para():
    param = request.args.get('a', "하하하")
    return 'para' + param

@app.route('/post',methods=['POST'])
def post():
    value = request.form['b']
    return 'post' + value

@app.route('/list')
def list():
    a = "홍길동"
    b = ["한국", "브라질", "칠레"]
    c = [
            {'e_id':'1', 'e_name':'1', 'sex':'1', 'addr':'1'},
            {'e_id':'2', 'e_name':'2', 'sex':'2', 'addr':'2'}
        ]
    return render_template('list.html', a = a, b = b, c = c)


if __name__ == '__main__':
    app.run()
