import sys
from PyQt5 import uic
from PyQt5.QtWidgets import QApplication, QMainWindow
from selenium import webdriver
from selenium.webdriver.common.by import By
from day14.mynmap_dao import DaoNmap


form_class = uic.loadUiType("mynmap_gui.ui")[0]

class MainClass(QMainWindow, form_class):
    def __init__(self) :
        QMainWindow.__init__(self)
        self.dn = DaoNmap()
        self.browser = webdriver.Chrome()
        self.browser.get("https://place.map.kakao.com/1636040232")
        self.setupUi(self)
        self.show()
        self.pb.clicked.connect(self.myclick)
        self.pb_scrap.clicked.connect(self.myscrap)
    
    def myclick(self):
        url = self.le.text()
        self.browser.get(url)
        
    def myscrap(self):  
        ims = self.browser.find_elements(by=By.CSS_SELECTOR, value='.info_menu')
        store = self.browser.find_elements(by=By.CSS_SELECTOR, value='.tit_location')[1].text
        
        for im in ims:
            m_name = im.find_elements(by=By.CSS_SELECTOR, value='.loss_word')[0].text
            price = im.find_elements(by=By.CSS_SELECTOR, value='.price_menu')[0].text.replace(",","")
            cnt = self.dn.myinsert(store, m_name, price)
            print(cnt,store,m_name,price)

            
    
        
if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    window = MainClass() 
    app.exec_()