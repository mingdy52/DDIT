import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
from selenium import webdriver
from selenium.webdriver.common.by import By
from day14.mymango_dao import DaoMango


#UI파일 연결
#단, UI파일은 Python 코드 파일과 같은 디렉토리에 위치해야한다.
form_class = uic.loadUiType("mymango_gui.ui")[0]

#화면을 띄우는데 사용되는 Class 선언
class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        QMainWindow.__init__(self)
        self.browser = webdriver.Chrome()
        self.browser.get('https://www.mangoplate.com/search/%EB%8C%80%EC%A0%84%20%EC%98%A4%EB%A5%98%EB%8F%99')
        self.setupUi(self)
        self.show()
        self.pb.clicked.connect(self.myclick)
        self.pb_scrap.clicked.connect(self.myscrap)
        self.dm = DaoMango()
        
    def myclick(self):
        url = self.le.text()
        self.browser.get(url)
       
    def myscrap(self):
        figs = self.browser.find_elements(by=By.CSS_SELECTOR, value = 'figcaption')
        for fig in figs :
            try:
                m_name = fig.find_elements(by=By.CSS_SELECTOR, value = 'a')[0].text
                if m_name != "" :
                    menu = fig.find_elements(by=By.CSS_SELECTOR, value = '.etc')[0].find_elements(by=By.CSS_SELECTOR, value = 'span')[0].text
                    print(":", m_name, ":", menu)
                    cnt = self.dm.myinsert(m_name, menu)
                    print(cnt)
            except:
                print("error:", m_name, ":", menu)


        
if __name__ == "__main__" :
    #QApplication : 프로그램을 실행시켜주는 클래스
    app = QApplication(sys.argv) 

    #WindowClass의 인스턴스 생성
    myWindow = WindowClass() 

    #프로그램을 이벤트루프로 진입시키는(프로그램을 작동시키는) 코드
    app.exec_()
