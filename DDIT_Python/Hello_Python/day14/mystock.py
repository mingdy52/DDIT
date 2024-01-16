import requests
from bs4 import BeautifulSoup
import pymysql
import datetime

conn = pymysql.connect(host='localhost', user='root', password='python', db='python', charset='utf8', port=3305)
cursor = conn.cursor()

now = datetime.datetime.now()
ymd = now.strftime("%Y%m%d.%H%M")

print(ymd)

htmls =requests.get("https://vip.mk.co.kr/newSt/rate/item_all.php")
htmls.encoding = "euc-kr"

soup = BeautifulSoup(htmls.text, "html.parser")

st2s =soup.select(".st2")


for st in st2s :
    s_name = st.text
    s_code = st.select("a")[0]['title']
    price = st.parent.select("td")[1].text.replace(",","")
    print(s_name, s_code, price)
    sql = f"""INSERT INTO stock VALUES ('{s_code}','{s_name}','{price}','{ymd}')"""

    print("sql",sql)
    cnt = cursor.execute(sql)
    print("cnt",cnt)
    
conn.commit()
cursor.close()
conn.close()