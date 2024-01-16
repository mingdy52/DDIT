import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
import random


form_class = uic.loadUiType("pyqt07.ui")[0]


class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        QMainWindow.__init__(self)
        self.setupUi(self)
        self.show()
        self.pb.clicked.connect(self.myclick)
        
        
    def myclick(self):
        le1 = int(self.le1.text())
        le2 = int(self.le2.text())
        le3 = int(self.le3.text())
        result = 0
        
        
        for i in range(le1, le2+1) :
            if i % le3 == 0 :
                result += i
        

        
        self.le4.setText(str(result))
        


        
if __name__ == "__main__" :
    app = QApplication(sys.argv) 

    myWindow = WindowClass() 

    app.exec_()
 

