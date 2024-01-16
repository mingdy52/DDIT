import random

arr9 = [1,2,3,4,5,6,7,8,9]
arr3 = []

for i in range(3):
    rnd = int(len(arr9)*random.random())
    p = arr9.pop(rnd)
    arr3.append(p)

print("p", p)
print(arr9)
print(arr3)
