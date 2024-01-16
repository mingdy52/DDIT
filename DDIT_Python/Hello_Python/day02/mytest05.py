# 1에서 9까지의 수 중에서 랜덤으로 중복없이 3개를 출력하시오.
import random

arr = [1,2,3,4,5,6,7,8,9]

arr3 = []

while True :
    rnd = int(random.random() * 9)
    
    if arr[rnd] > -1 :
        arr3.append(arr[rnd])
        arr[rnd] = -1
        
    if len(arr3) >= 3 : 
        break;

print(arr3)