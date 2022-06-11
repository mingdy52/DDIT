dan = input("출력할 단수를 입력하세요")

ddan = int(dan)

result = 0
for i in range(1,9+1):
    result = ddan * i
    print(f"{ddan} * {i} = {result}")
    