'''def gcd(a,b):
    while b!=0:
        a,b=b,a%b
    return a
a=int(input('Enter no1'))
b=int(input('Enter no2'))
a=gcd(a,b)
print(a)'''
def gcd(a,b):
    if b==0:
        return a
    else:
        return gcd(b,a%b)
a=int(input('Enter no1'))
b=int(input('Enter no2'))
a=gcd(a,b)
print(a)