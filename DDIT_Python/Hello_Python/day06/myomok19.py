import sys
from PyQt5 import uic, QtGui, QtCore
from PyQt5.QtWidgets import QApplication, QMainWindow, QLabel, QPushButton
from PyQt5.Qt import QPixmap, QMessageBox


form_class = uic.loadUiType("myomok19.ui")[0]

class MainClass(QMainWindow, form_class):
    def __init__(self) :
        QMainWindow.__init__(self)
        self.setupUi(self)
        self.flag_wb = True
        self.flag_over = False
        self.arr2d = [
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0],
            [0,0,0,0,0, 0,0,0,0,0, 0,0,0,0,0, 0,0,0,0]
        ]
        self.dol2d = []
        
        for i in range(19):
            line = []
            for j in range(19):
                doll = QPushButton("",self)
                doll.setIcon(QtGui.QIcon('0.png'))
                doll.setIconSize(QtCore.QSize(40,40))
                doll.setToolTip("{},{}".format(i,j))
                doll.setGeometry(QtCore.QRect(j*40, i*40, 40, 40))
                doll.clicked.connect(self.myclick)
                line.append(doll)
            self.dol2d.append(line)
        
        self.pb.clicked.connect(self.myreset)
        self.show()
        self.myrender()
    
    def myreset(self):
        self.flag_over = False
        self.flag_wb = True
        for i in range(19):
            for j in range(19):
                self.arr2d[i][j] = 0
        self.myrender()
    
    def myrender(self):
        for i in range(19):
            for j in range(19):
                if self.arr2d[i][j] == 0:
                    self.dol2d[i][j].setIcon(QtGui.QIcon('0.png'))
                if self.arr2d[i][j] == 1:
                    self.dol2d[i][j].setIcon(QtGui.QIcon('1.png'))
                if self.arr2d[i][j] == 2:
                    self.dol2d[i][j].setIcon(QtGui.QIcon('2.png')) 
                    
    def checkUP(self,i,j,stone):
        cnt = 0
        while True:
            i -= 1
            if i < 0:
                return cnt
            if self.arr2d[i][j]==stone:
                cnt += 1
            else:
                return cnt
    
    def checkDW(self,i,j,stone):
        cnt = 0
        try:
            while True:
                i += 1
                if i < 0:
                    return cnt
                if self.arr2d[i][j] == stone:
                    cnt += 1
                else:
                    return cnt
        except:
            return cnt
    
    def checkRI(self,i,j,stone):
        cnt = 0
        try:
            while True:
                j += 1
                if j < 0:
                    return cnt
                if j > 0:
                    return cnt
                if self.arr2d[i][j] == stone:
                    cnt += 1
                else:
                    return cnt
        except:
            return cnt
    
    def checkle(self,i,j,stone):
        cnt = 0
        try:
            while True:
                j -= 1
                if j < 0:
                    return cnt
                if j > 0:
                    return cnt
                if self.arr2d[i][j] == stone:
                    cnt += 1
                else:
                    return cnt
        except:
            return cnt
    
    def checkur(self,i,j,stone):
        cnt = 0
        try:
            while True:
                i -= 1
                j += 1
                if j < 0:
                    return cnt
                if i < 0:
                    return cnt
            
                if self.arr2d[i][j] == stone:
                    cnt += 1
                else:
                    return cnt
        except:
            return cnt
        
    def checkul(self,i,j,stone):
        cnt = 0
        try:
            while True:
                i -= 1
                j -= 1
                if j < 0:
                    return cnt
                if i < 0:
                    return cnt
            
                if self.arr2d[i][j] == stone:
                    cnt += 1
                else:
                    return cnt
        except:
            return cnt
    
    def checkdr(self,i,j,stone):
        cnt = 0
        try:
            while True:
                i += 1
                j += 1
                if j < 0:
                    return cnt
                if i < 0:
                    return cnt
            
                if self.arr2d[i][j] == stone:
                    cnt += 1
                else:
                    return cnt
        except:
            return cnt
        
    def checkdl(self,i,j,stone):
        cnt = 0
        try:
            while True:
                i += 1
                j -= 1
                if j < 0:
                    return cnt
                if i < 0:
                    return cnt
            
                if self.arr2d[i][j] == stone:
                    cnt += 1
                else:
                    return cnt
        except:
            return cnt
    
                    
    def myclick(self):
        str_ij = self.sender().toolTip()
        arr_ij = str_ij.split(",")
        i = int(arr_ij[0])
        j = int(arr_ij[1])
        
        if self.arr2d[i][j] > 0:
            return
        
        stone = -1
        if self.flag_wb :
            self.arr2d[i][j] = 1
            stone = 1
        else:
            self.arr2d[i][j] = 2  
            stone = 2
        up = self.checkUP(i,j,stone)
        dw = self.checkDW(i, j, stone)
        ri = self.checkRI(i, j, stone)
        le = self.checkle(i, j, stone)
        
        ur = self.checkur(i, j, stone)
        ul = self.checkul(i, j, stone)
        dr = self.checkdr(i, j, stone)
        dl = self.checkdl(i, j, stone)
        

        # print("up",up)
        # print("dw",dw)
        # print("ri",ri)
        # print("le",le)
        #
        # print("ur",ur)
        # print("ul",ul)
        # print("dr",dr)
        # print("dl",dl)
        
        d1 = up+dw+1
        d2 = ur+dl+1
        d3 = ri+le+1
        d4 = ul+dr+1

       
        if d1 == 5 or d2 == 5 or d3 == 5 or d4 == 5:
            if self.flag_wb:
                QMessageBox.about(self, "omok", "백돌승리")
            else:
                QMessageBox.about(self, "omok", "흑돌승리")
        self.myrender()        
        
        self.flag_wb = not self.flag_wb
        
if __name__ == "__main__" :
    app = QApplication(sys.argv) 
    window = MainClass() 
    app.exec_()
    
    
