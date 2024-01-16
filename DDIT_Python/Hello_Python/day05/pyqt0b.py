from PyQt5 import uic
from PyQt5.QtWidgets import QMainWindow, QApplication, QMessageBox
import sys
import random


form_class = uic.loadUiType("pyqt0b.ui")[0]

class MyWindow(QMainWindow, form_class):
    
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.pb.clicked.connect(self.btn_clicked)
        self.le.returnPressed.connect(self.btn_clicked)
        self.arr10 = list(range(0,9+1))
        self.arr3 = []
        self.setCom()
        
    def setCom(self):
        for i in range(3):
            rnd = int(len(self.arr10) * random.random())
            p = self.arr10.pop(rnd)
            self.arr3.append(p)
        print(self.arr3)
        
        
    def btn_clicked(self):
        self.strike = 0
        self.ball = 0
        self.mine = list(self.le.text())
        self.result(self.strike, self.ball)
        
        
    def result(self,strike,ball):
        for i in range(len(self.mine)):
            for j in range(len(self.arr3)):
                if self.arr3[j] == int(self.mine[i]) and j == i:
                    self.strike +=1
                elif self.arr3[j] == int(self.mine[i]) and not j ==i:
                    self.ball +=1 
        if not self.strike == 3:             
            self.te.append(str(self.strike) + "K" + str(self.ball) + "B")
        else:
            self.te.append("게임 종료")
            self.call()
            
            
        self.le.setText("");
        
    def call(self):
       QMessageBox.about(self,"야구게임 승리!","드디어 승리")
       
           
if __name__ == '__main__':
    app = QApplication(sys.argv)
    myWindow = MyWindow()
    myWindow.show()
    app.exec_()
