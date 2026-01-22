'''def fib(n):
    if n<=1:
        return n
    else:
        return fib(n-1) + fib(n-2)
n=int(input('Enter no'))
a=fib(n)
print(a)'''
def fib(n):
    if n<=1:
        return n
    else:
        a,b=0,1
        for i in range(2,n):
            a,b=0,1
            a,b=a,a+b
    return b
n=int(input('Enter no'))
print(fib(n))


    