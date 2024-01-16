from selenium import webdriver
import time
from selenium.webdriver.common.by import By

browser = webdriver.Chrome()
browser.get('http://127.0.0.1:5000/')

trs = browser.find_elements(by=By.CSS_SELECTOR, value = "#tb > tr")

for tr in trs :
    tds = tr.find_elements(by=By.CSS_SELECTOR, value = "td")
    print(tds[1].text, tds[3].text)
    
# time.sleep(5)
# browser.quit()