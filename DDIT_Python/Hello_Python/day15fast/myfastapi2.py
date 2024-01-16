from typing import Optional
from fastapi import FastAPI
from day15fast.dao_emp import DaoEmp

app = FastAPI()
de = DaoEmp()

# uvicorn myfastapi2:app --reload

@app.get("/emp_one.ajax")
def emp_one_ajax():
    list = de.myselects()
    return list