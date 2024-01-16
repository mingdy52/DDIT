import pymysql

class DaoEmp:
    def __init__(self):
        self.conn = pymysql.connect(host="localhost", user="root", password="python", db="python", charset="utf8", port=3305)
        self.cursor = self.conn.cursor(pymysql.cursors.DictCursor) 

    
    def myselects(self):
        sql = "select * from emp" 
        self.cursor.execute(sql) 
        
        rows = self.cursor.fetchall()
        return rows
    
    def myselect(self, e_id):
        sql = f"select * from emp where e_id = '{e_id}'" 
        self.cursor.execute(sql) 
        
        emp = self.cursor.fetchall()
        return emp[0]
    
    # def myinsert(self, e_id, e_name, sex, addr):
    #     sql = f"INSERT INTO emp VALUES ('{e_id}','{e_name}','{sex}','{addr}')"
    #     cnt = self.cursor.execute(sql) 
    #     self.conn.commit()
    #
    #     return cnt
    
    # def myupdate(self, e_id, e_name, sex, addr):
    #     sql = f"""
    #         UPDATE emp 
    #         set e_name = '{e_name}', sex = '{sex}', addr = '{addr}'
    #         where e_id = '{e_id}' """
    #
    #     cnt2 = self.cursor.execute(sql) 
    #     self.conn.commit()
    #
    #     return cnt2
    
    def mydelete(self, e_id):
        sql = f"""
            delete from emp 
            where e_id = '{e_id}' """
        cnt3 = self.cursor.execute(sql) 
        self.conn.commit()
        
        return cnt3

        
    def __del__(self):
        self.cursor.close()
        self.conn.close() 
    
if __name__=='__main__':
    de = DaoEmp()
    rows = de.myselects()
    emp = de.myselect('1')
    # cnt = de.myinsert('5','5','5','5')
    # cnt2 = de.myupdate('5','6','6','6')
    cnt3 = de.mydelete('5')
    print(rows)
    print(emp)
    # print(cnt)
    # print(cnt2)
    print(cnt3)
