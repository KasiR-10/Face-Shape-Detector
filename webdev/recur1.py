'''def factorial(n):
    if n==0:
        return 1
    else:
        return n*factorial(n-1)
n=int(input('Enter no'))
a=factorial(n)
print(a)'''
num=int(input('enter no'))
fact=1
while num>1:
    fact=fact*num
    num=num-1
print('factorial is:',fact)
