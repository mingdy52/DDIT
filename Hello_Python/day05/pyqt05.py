import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
import random


form_class = uic.loadUiType("pyqt05.ui")[0]


class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        QMainWindow.__init__(self)
        self.setupUi(self)
        self.show()
        self.pb.clicked.connect(self.myclick)
        self.le_mine.returnPressed.connect(self.myclick)
        
        
    def myclick(self):
        mine = self.le_mine.text()
        com = ""
        result = ""
        
        rnd = random.random()
        
        if rnd > 0.5 :
            com = "홀"
        else :
            com = "짝"
        
        
        if mine == com :
            result = "이김"
                    
        else :
            result = "짐"
            
        self.le_com.setText(com)
        self.le_result.setText(result)
        



        
if __name__ == "__main__" :
    app = QApplication(sys.argv) 

    myWindow = WindowClass() 

    app.exec_()
 

