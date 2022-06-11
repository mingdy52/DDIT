import sys
from PyQt5.QtWidgets import *
from PyQt5 import uic


formclass = uic.loadUiType("pyqt0a.ui")[0]

class MyWindow(QMainWindow, formclass):
    
    
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.myClick)
    
    def drawStar(self, cnt):  
        ret = ""
        for i in range(cnt):
            ret += "*" 
        ret += "\n"
        return ret
        
    def myClick(self):
        first = int(self.le_first.text())
        last = int(self.le_last.text())
        result = ""
        for i in range(first,last+1):
            result += self.drawStar(i)
        # for i in range(first,last-1,-1):
        #     result += self.drawStar(i)

        self.te.setText(result)     
    

if __name__ == "__main__":
    app = QApplication(sys.argv)
    myWindow = MyWindow()
    myWindow.show()
    app.exec_()