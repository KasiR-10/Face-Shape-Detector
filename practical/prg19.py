a=int(input('Enter the number'))
b=int(input('Enter the number'))
def gcd(a,b):
    if b==0:
        return a
    else:
        return gcd(b,a%b)
print(gcd(a,b))