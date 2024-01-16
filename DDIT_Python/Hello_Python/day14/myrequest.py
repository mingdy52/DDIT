import requests
from bs4 import BeautifulSoup

htmls =requests.get("http://127.0.0.1:5000/")
# print(htmls.text)

soup = BeautifulSoup(htmls.text, "html.parser")

trs =soup.find_all("tr")


for idx, tr in enumerate(trs) :
    if idx > 0:
        tds = tr.find_all("td")
        print(tds[1].text, "\t", tds[3].text)

# for idx, tr in enumerate(trs) :
#     if idx > 0:
#         tds = tr.select("td")
#         print(tds[1].text, "\t", tds[3].text)