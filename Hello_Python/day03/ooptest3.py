class Xi :
    def __init__(self):
        self.cnt_nuclear = 100
    
    def command(self) :
        self.cnt_nuclear += 1
    
class Lee :
    def __init__(self):
        self.money = 100
    
    def galguda(self, power) :
        self.money += power
    
class Putin :
    def __init__(self):
        self.army_power = 100
    
    def goToUkraine(self) :
        self.army_power -= 1    
    
    
class Sin(Xi, Lee, Putin) :
    def __init__(self):
        # super().__init__()
        Xi.__init__(self)
        Lee.__init__(self)
        Putin.__init__(self)


if __name__=='__main__':
    
    sin = Sin()
    print("cnt_nuclear", sin.cnt_nuclear)
    print("money", sin.money)
    print("army_power", sin.army_power)
    
    sin.command();
    sin.galguda(5);
    sin.goToUkraine();
        
    print("cnt_nuclear", sin.cnt_nuclear)
    print("money", sin.money)
    print("army_power", sin.army_power)
