import pymysql

class DaoNmap:
    def __init__(self):
        self.conn = pymysql.connect(host='localhost', user='root', password='python',
                               db='python',port=3305, charset='utf8')
        self.curs = self.conn.cursor(pymysql.cursors.DictCursor)

    
    def myinsert(self,store,m_name,price):
        sql = f"""insert into menu (store,m_name,price) 
                values ('{store}','{m_name}','{price}')"""
        cnt = -1
        try:
            cnt = self.curs.execute(sql)
            self.conn.commit()
        except:
            print("DaoNmap:error")
        
        return cnt

    
    def __del__(self):
        self.curs.close()
        self.conn.close()
    
if __name__ == '__main__':
    de = DaoNmap()
    cnt = de.myinsert('1','1','1')
    print(cnt)
    
    
    
    
    