from PyQt5 import uic
from PyQt5.QtWidgets import QMainWindow, QApplication, QLineEdit
import sys


form_class = uic.loadUiType("pyqt02.ui")[0]

class MyWindow(QMainWindow, form_class):
    
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.show()
        self.pb.clicked.connect(self.myclick)
        
    def myclick(self):
        qle = QLineEdit()
        qle.text()
        
        a = self.le.text()
        aa = int(a)
        aa -= 2
        self.le.setText(str(aa))
        
if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWindow = MyWindow()
    app.exec_()
