import pymysql

class DaoStudent:
    def __init__(self):
        self.conn = pymysql.connect(host="localhost", user="root", password="python", db="python", charset="utf8", port=3305)
        self.cursor = self.conn.cursor(pymysql.cursors.DictCursor) 

    
    def mystudents(self):
        sql = "select * from student" 
        self.cursor.execute(sql) 
        
        rows = self.cursor.fetchall()
        return rows
    
    def mystudent(self, s_id):
        sql = f"select * from student where s_id = '{s_id}'" 
        self.cursor.execute(sql) 
    
        student = self.cursor.fetchall()
        return student[0]
    
    def myinsert(self, s_id, s_name, tel):
        sql = f"INSERT INTO student VALUES ('{s_id}','{s_name}','{tel}')"
        cnt = self.cursor.execute(sql) 
        self.conn.commit()
    
        return cnt
    
    def myupdate(self, s_id, s_name, tel):
        sql = f"""
            UPDATE student 
            set s_name = '{s_name}', tel = '{tel}'
            where s_id = '{s_id}' """
    
        cnt2 = self.cursor.execute(sql) 
        self.conn.commit()
    
        return cnt2
    
    def mydelete(self, s_id):
        sql = f"""
            delete from student 
            where s_id = '{s_id}' """
        cnt3 = self.cursor.execute(sql) 
        self.conn.commit()
        
        return cnt3

        
    def __del__(self):
        self.cursor.close()
        self.conn.close() 
    
if __name__=='__main__':
    de = DaoStudent()
    rows = de.mystudents()
    print(rows)