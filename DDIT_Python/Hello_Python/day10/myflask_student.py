from flask import Flask
import pymysql
from flask.templating import render_template
from flask.globals import request
from conda_build.api import debug
from day10.dao_student import DaoStudent

app = Flask(__name__)
de = DaoStudent()

@app.route('/')
@app.route('/student')
def student():
    students = de.mystudents()
    return render_template('student.html',  students = students)

@app.route('/student_add')
def student_add():
    return render_template('student_add.html')

@app.route('/student_add_act', methods=['POST'])
def student_add_act():
    s_id = request.form['s_id']
    s_name = request.form['s_name']
    tel = request.form['tel']
    cnt = -1
    try:
        cnt = de.myinsert(s_id, s_name, tel)
    except:
        print("insert : error")

    return render_template('student_add_act.html', cnt = cnt)

@app.route('/student_mod')
def student_mod():
    s_id = request.args.get('s_id','')
    student = de.mystudent(s_id)

    return render_template('student_mod.html', student=student)

@app.route('/student_mod_act', methods=['POST'])
def student_mod_act():
    s_id = request.form['s_id']
    s_name = request.form['s_name']
    tel = request.form['tel']
    cnt = -1
    try:
        cnt = de.myupdate(s_id, s_name, tel)
    except:
        print("dao update : error")
    return render_template('student_mod_act.html', cnt = cnt)

@app.route('/student_del')
def student_del():
    s_id = request.args.get('s_id','')
    cnt = -1
    try:
        cnt = de.mydelete(s_id)
    except:
        print("dao delete : error")
    return render_template('student_del.html', cnt=cnt)


if __name__ == '__main__':
    app.run(debug=True)
