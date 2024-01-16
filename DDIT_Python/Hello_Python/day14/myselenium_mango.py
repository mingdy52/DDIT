from selenium import webdriver
from selenium.webdriver.common.by import By

browser = webdriver.Chrome()
browser.get('https://www.mangoplate.com/search/대전%20오류동')

trs = browser.find_elements(by=By.CSS_SELECTOR, value = ".info")
# trs = browser.find_elements(by=By.CSS_SELECTOR, value = "h2.title")
# sp = browser.find_elements(by=By.CSS_SELECTOR, value = "p > span")

for tr in trs :
    h2 = tr.find_elements(by=By.CSS_SELECTOR, value = "h2")
    sp = tr.find_elements(by=By.CSS_SELECTOR, value = "p > span")
    print(h2[0].text, sp[0].text)
    

