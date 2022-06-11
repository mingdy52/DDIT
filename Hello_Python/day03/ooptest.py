class Animal:
    def __init__(self):
        print("Animal:__init__")
        self.age = 1
    def getOld(self):
        self.age += 1
    def __del__(self):
        print("Animal:__del__")
    
class Cat(Animal):
    def __init__(self):
        print("Cat:__init__")
        super().__init__()
        self.ssagaji = False
    def chur(self):
        self.ssagaji = True
    def __del__(self):
        print("Cat:__del__")
        

if __name__=='__main__':
    
    ani = Animal()
    print(ani.age)
    ani.getOld()
    print(ani.age)
    
    cat = Cat()
    print(cat.age)
    print(cat.ssagaji)
    cat.getOld()
    cat.chur()
    print(cat.age)
    print(cat.ssagaji)

