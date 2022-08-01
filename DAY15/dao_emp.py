import pymysql

class DaoEmp:
    def __init__(self):
        self.conn = pymysql.connect(host='localhost', user='root', password='python',
                               db='python',port=3305, charset='utf8')
        self.curs = self.conn.cursor(pymysql.cursors.DictCursor)
        
    def myselects(self):
        sql = "select * from emp"
        self.curs.execute(sql)
        
        rows = self.curs.fetchall()
        return rows
    
    def myselect(self,e_id):
        sql = f"""
        select 
            e_id,e_name,sex,addr
        from emp
        where
            e_id = '{e_id}'
        """
        self.curs.execute(sql)
        
        rows = self.curs.fetchall()
        return rows[0]
    
    def myinsert(self,e_id,e_name,sex,addr):
        sql = f"""insert into emp (e_id,e_name,sex,addr) 
                values ('{e_id}','{e_name}','{sex}','{addr}')"""
                
        print("sql",sql)
        
        cnt = self.curs.execute(sql)
        self.conn.commit()
        return cnt
    
    def myupdate(self,e_id,e_name,sex,addr):
        sql = f"""
            update emp
            set 
                e_name='{e_name}',
                sex='{sex}',
                addr='{addr}'
            where 
                e_id='{e_id}'
        """
        cnt = self.curs.execute(sql)
        self.conn.commit()
        return cnt
    
    def mydelete(self,e_id):
        sql = f"""
            delete from emp
            where 
                e_id='{e_id}'
        """
        cnt = self.curs.execute(sql)
        self.conn.commit()
        return cnt
    
    def __del__(self):
        self.curs.close()
        self.conn.close()
    
if __name__ == '__main__':
    de = DaoEmp()
    cnt = de.mydelete('5')
    print(cnt)
    
    
    
    
    