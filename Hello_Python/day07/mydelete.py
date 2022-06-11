import pymysql

conn = pymysql.connect(host='localhost', user='root', password='python', db='python', charset='utf8', port=3305)


e_id = '5'

cursor = conn.cursor()
sql = f"""
    delete from emp 
    where e_id = '{e_id}' """

print(sql)
cnt = cursor.execute(sql)
print(cnt)

conn.commit()
cursor.close()
conn.close()