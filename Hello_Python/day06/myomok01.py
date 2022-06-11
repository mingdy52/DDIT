from PyQt5 import uic
from PyQt5.QtWidgets import QMainWindow, QApplication
import sys
from PyQt5.QtGui import QPixmap
from PyQt5.Qt import QLabel


form_class = uic.loadUiType("myomok01.ui")[0]

class MyWindow(QMainWindow, form_class):
    
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        
        pixmap = QPixmap('0.png')
        mylbl = QLabel("sdfs", self)
        mylbl.setPixmap(pixmap)
        
        mylb2 = QLabel("123", self)
        mylb2.move(30,0)
        mylb2.setPixmap(pixmap)
        
        
        self.show()
        self.pb.clicked.connect(self.myclick)
        self.lbl.clicked.connect(self.mychange)
        
        
    def myclick(self):
        self.lbl.setText("good")
       
        
    def mychange(self):
        pixmap = QPixmap('1.png')
        mylbl = QLabel("sdfs", self)
        mylbl.setPixmap(pixmap)
       
        
if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWindow = MyWindow()
    myWindow.show()
    app.exec_()
