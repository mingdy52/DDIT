import random

com=""
main = ""
result= ""

mine = input ("홀/짝을 입력하시오")
rnd = random.random()

if rnd > 0.5:
    com = "홀"

else:
    com = "홀"

if com == mine:
    result = "이김"
else:
    result = "짐"

print("main",main)
print("com",com)
print("result",result)
