from flask import Flask
import pymysql
from flask.templating import render_template
from flask.globals import request
from day08.dao_emp import DaoEmp

app = Flask(__name__)

@app.route('/')
@app.route('/emp')
def list():
    # c = [
    #         {'e_id':'1', 'e_name':'1', 'sex':'1', 'addr':'1'},
    #         {'e_id':'2', 'e_name':'2', 'sex':'2', 'addr':'2'}
    #     ]
    de = DaoEmp()
    emps = de.myselects()
    return render_template('emp.html',  c = emps)


if __name__ == '__main__':
    app.run()
