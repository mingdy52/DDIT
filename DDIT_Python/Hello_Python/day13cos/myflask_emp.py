from flask import Flask
import pymysql
from flask.templating import render_template
from flask.globals import request
from conda_build.api import debug
from flask.json import jsonify

from flask_cors.extension import CORS
from day13cos.dao_emp import DaoEmp

app = Flask(__name__, static_url_path='')
CORS(app)
de = DaoEmp()

@app.route('/')
@app.route('/emp')
def emp():
    return render_template('emp.html')

@app.route('/emp.ajax', methods=['POST'])
def emp_ajax():
    data = request.get_json()
    print("data",data['e_id'])
    return jsonify({'msg': '저장 완료!'})

@app.route('/emp_list.ajax', methods=['POST'])
def emp_list_ajax():
    list = de.myselects()
    return jsonify(list)

@app.route('/emp_one.ajax', methods=['POST'])
def emp_one_ajax():
    data = request.get_json()
    e_id = data['e_id']
    emp = de.myselect(e_id)
    
    return jsonify(emp)

@app.route('/emp_add.ajax', methods=['POST'])
def emp_add_ajax():
    data = request.get_json()
    e_id = data['e_id']
    e_name = data['e_name']
    sex = data['sex']
    addr = data['addr']
    
    cnt = -1
    try:
        cnt = de.myinsert(e_id, e_name, sex, addr)
    except:
        print("insert : error")
    return jsonify({'cnt':cnt})

@app.route('/emp_mod.ajax', methods=['POST'])
def emp_mod_ajax():
    data = request.get_json()
    e_id = data['e_id']
    e_name = data['e_name']
    sex = data['sex']
    addr = data['addr']
    cnt = -1
    try:
        cnt = de.myupdate(e_id, e_name, sex, addr)
    except:
        print("update : error")
    return jsonify({'cnt':cnt})

@app.route('/emp_del.ajax', methods=['POST'])
def emp_del_ajax():
    data = request.get_json()
    e_id = data['e_id']
    cnt = -1
    try:
        cnt = de.mydelete(e_id)
    except:
        print("delete : error")
    return jsonify({'cnt':cnt})

if __name__ == '__main__':
    app.run(debug=True)
