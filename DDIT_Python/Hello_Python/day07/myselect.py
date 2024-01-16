import pymysql

# pymysql.connect(host=서버IP주소, user=사용자, passoword=암호, db=데이터베이스, charset=문자세트)
conn = pymysql.connect(host="localhost", user="root", password="python", db="python", charset="utf8", port=3305)
cursor = conn.cursor(pymysql.cursors.DictCursor) 

sql = "select * from emp" 

cursor.execute(sql) 
# cursor: 자바의 statement

rows = cursor.fetchall()

print(rows)
    
cursor.close()
conn.close() 