import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic
import random


form_class = uic.loadUiType("pyqt06.ui")[0]


class WindowClass(QMainWindow, form_class) :
    def __init__(self) :
        QMainWindow.__init__(self)
        self.setupUi(self)
        self.show()
        self.pb.clicked.connect(self.myclick)
        
        
    def myclick(self):
        arr45 = list(range(1, 45+1))
        arr6 = []
        
        for i in range(6):
            rnd = int(len(arr45)*random.random())
            p = arr45.pop(rnd)
            arr6.append(p)
        

        
        self.lbl_1.setText(str(arr6[0]))
        self.lbl_2.setText(str(arr6[1]))
        self.lbl_3.setText(str(arr6[2]))
        self.lbl_4.setText(str(arr6[3]))
        self.lbl_5.setText(str(arr6[4]))
        self.lbl_6.setText(str(arr6[5]))
        


        
if __name__ == "__main__" :
    app = QApplication(sys.argv) 

    myWindow = WindowClass() 

    app.exec_()
 

