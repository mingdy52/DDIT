import pymysql

class DaoMango:
    def __init__(self):
        self.conn = pymysql.connect(host="localhost", user="root", password="python", db="python", charset="utf8", port=3305)
        self.cursor = self.conn.cursor(pymysql.cursors.DictCursor) 

    def myinsert(self, m_name, menu):
        sql = f"INSERT INTO mangoplate VALUES ('{m_name}','{menu}')"
        cnt = self.cursor.execute(sql) 
        self.conn.commit()
    
        return cnt
    
if __name__=='__main__':
    de = DaoMango()
    cnt = de.myinsert('1', '1')
    print(cnt)
