import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
import random


form_class = uic.loadUiType("pyqt09.ui")[0]


class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        QMainWindow.__init__(self)
        self.setupUi(self)
        self.show()
        self.pb.clicked.connect(self.myclick)
        self.leMine.returnPressed.connect(self.myclick)
        
        
    def myclick(self):
        mine = self.leMine.text()
        com = ""
        result = ""
        
        
        rnd = random.random()
        
        if rnd > 0.66 :
            com = "가위"
        elif rnd > 0.33 :
            com = "바위"
        else :
            com = "보"
            
      
        
        if mine == com : result = "비김"
        
        if mine == "가위" and com == "보" : result = "이김"
        if mine == "바위" and com == "가위" : result = "이김"
        if mine == "보" and com == "바위" : result = "이김"
        
        if mine == "보" and com == "가위" : result = "짐"
        if mine == "바위" and com == "보" : result = "짐"
        if mine == "가위" and com == "바위" : result = "짐"
        
        
        self.leCom.setText(com)
        self.leResult.setText(result)
        



        
if __name__ == "__main__" :
    app = QApplication(sys.argv) 

    myWindow = WindowClass() 

    app.exec_()
 

