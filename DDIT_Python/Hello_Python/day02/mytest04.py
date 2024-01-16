# 1에서 9까지의 수 중에서 랜덤으로 중복없이 3개를 출력하시오.
import random

arr = [1,2,3,4,5,6,7,8,9]

for i in range(10):
    rnd = int(random.random() * 9)
    a = arr[rnd]
    b = arr[0]
    arr[0] = a
    arr[rnd] = b

print(arr[0], arr[1], arr[2])
    