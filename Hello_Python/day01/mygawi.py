import random

com = ""
man = ""
result = ""

man = input("가위/바위/보 중 하나를 입력하세요")

rnd = random.random()

if rnd >= 0.7:
    com = "가위"
elif rnd >= 0.4:
    com = "바위"
else:
    com = "보"

if com == man:
    result = "비김"
elif (com == "가위" and man == "바위") or (com == "바위" and man == "보") or (com == "보" and man == "가위"):
    result = "이김"
else:
    result = "짐"

print("com",com)
print("man",man)
print("result",result)