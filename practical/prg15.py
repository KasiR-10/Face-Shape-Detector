num=int(input('Enter number'))
rvr=0
while num>0:
    s=num%10
    rvr=(rvr*10)+s
    num=num//10
print(rvr)
   