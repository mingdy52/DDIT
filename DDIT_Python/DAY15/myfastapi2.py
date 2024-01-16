from typing import Optional

from fastapi import FastAPI
from dao_emp import DaoEmp
from pydantic.main import BaseModel
from starlette.middleware.cors import CORSMiddleware

class Emp(BaseModel):
    e_id: str
    e_name: Optional[str] = None
    sex: Optional[str] = None
    addr: Optional[str] = None
    


app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

#uvicorn  myfastapi2:app --reload

@app.post("/emp_one.ajax")
async def emp_one_ajax(emp: Emp):
    print("emp",emp)
    de = DaoEmp()
    emp = de.myselect(emp.e_id)
    return emp


@app.post("/emp_list.ajax")
async def emp_list_ajax():
    print("emp_list_ajax")
    de = DaoEmp()
    list = de.myselects()
    return list


@app.post("/emp_add.ajax")
async def emp_add_ajax(e: Emp):
    print("emp",e)
    de = DaoEmp()
    cnt = -1
    try:
        cnt = de.myinsert(e.e_id, e.e_name, e.sex, e.addr)
    except:
        print("dao : error")
    return {'cnt':cnt}


@app.post("/emp_mod.ajax")
async def emp_mod_ajax(e: Emp):
    print("emp",e)
    de = DaoEmp()
    cnt = -1
    try:
        cnt = de.myupdate(e.e_id, e.e_name, e.sex, e.addr)
    except:
        print("dao : error")
    return {'cnt':cnt}


@app.post("/emp_del.ajax")
async def emp_del_ajax(e: Emp):
    print("emp",e)
    de = DaoEmp()
    cnt = -1
    try:
        cnt = de.mydelete(e.e_id)
    except:
        print("dao : error")
    return {'cnt':cnt}



