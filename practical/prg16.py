def prime(n):
    flag=1
    for i in range(2,n):
        if n%i==0:
            return flag==0
    return flag==1
def noprime(x):
    for i in range(1,x):
        if prime(i)==1:
            print(i,end=' ')
x=int(input('Enter the value of n'))
noprime(x)
