import pymysql

conn = pymysql.connect(host='localhost', user='root', password='python', db='python', charset='utf8', port=3305)


e_id = '5'
e_name = '5'
sex = '5'
addr = '5'

cursor = conn.cursor()
sql = f"INSERT INTO emp VALUES ('{e_id}','{e_name}','{sex}','{addr}')"

print(sql)
cnt = cursor.execute(sql)
print(cnt)

conn.commit()
cursor.close()
conn.close()