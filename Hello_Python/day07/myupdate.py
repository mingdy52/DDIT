import pymysql

conn = pymysql.connect(host='localhost', user='root', password='python', db='python', charset='utf8', port=3305)


e_id = '5'
e_name = '6'
sex = '6'
addr = '6'

cursor = conn.cursor()
sql = f"""
    UPDATE emp 
    set e_name = '{e_name}', sex = '{sex}', addr = '{addr}'
    where e_id = '{e_id}' """

print(sql)
cnt = cursor.execute(sql)
print(cnt)

conn.commit()
cursor.close()
conn.close()