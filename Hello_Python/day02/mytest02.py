a = input("첫 수를 입력하시오")
b = input("끝 수를 입력하시오")
c = input("배수를 입력하시오")

aa = int(a)
bb = int(b)
cc = int(c)

d = 0
for i in range(aa,bb+1):
    if i % cc == 0 : 
        d += i
    
print(a + "에서 " + b + "까지 " + c + "의 배수의 합은 " + str(d) + "입니다.")
print("{}에서 {}까지 {}의 배수의 합은 {}입니다.".format(aa,bb,cc,d))
print(f"{aa}에서 {bb}까지 {cc}의 배수의 합은 {d}입니다.")
