num=int(input('Enter number'))
r=1
while num>0:
    s=num%10
    r*=s
    num=num//10
print(r)