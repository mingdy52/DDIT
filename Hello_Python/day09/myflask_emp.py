from flask import Flask
import pymysql
from flask.templating import render_template
from flask.globals import request
from conda_build.api import debug
from day09.dao_emp import DaoEmp

app = Flask(__name__)
de = DaoEmp()

@app.route('/')
@app.route('/emp')
def emp():
    emps = de.myselects()
    return render_template('emp.html',  emps = emps)

@app.route('/emp_add')
def emp_add():
    return render_template('emp_add.html')

@app.route('/emp_add_act', methods=['POST'])
def emp_add_act():
    e_id = request.form['e_id']
    e_name = request.form['e_name']
    sex = request.form['sex']
    addr = request.form['addr']
    cnt = -1
    try:
        cnt = de.myinsert(e_id, e_name, sex, addr)
    except:
        print("dao : error")
        
    return render_template('emp_add_act.html', cnt = cnt)

@app.route('/emp_mod')
def emp_mod():
    e_id = request.args.get('e_id','')
    emp = de.myselect(e_id)
    
    return render_template('emp_mod.html', emp=emp)

@app.route('/emp_mod_act', methods=['POST'])
def emp_mod_act():
    e_id = request.form['e_id']
    e_name = request.form['e_name']
    sex = request.form['sex']
    addr = request.form['addr']
    cnt = -1
    try:
        cnt = de.myupdate(e_id, e_name, sex, addr)
    except:
        print("dao : error")
    return render_template('emp_mod_act.html', cnt = cnt)

@app.route('/emp_del')
def emp_del():
    e_id = request.args.get('e_id','')
    cnt = -1
    try:
        cnt = de.mydelete(e_id)
    except:
        print("dao : error")
    return render_template('emp_del.html', cnt=cnt)


if __name__ == '__main__':
    app.run(debug=True)
