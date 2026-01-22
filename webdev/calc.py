def add(a,b):
    print('sum is: ',a+b)
def sub(a,b):
    print('difference is:',a-b)
def div(a,b):
    print('Quotient is ',a/b)
def mult(a,b):
    print('Multiplicative result',a*b)
def mod(a,b):
    print('Remainder:',a%b)
a=float(input('Enter no1: '))
b=int(input('Enter no2:'))
print('''OPERATIONS AVAILABLE 
                1:ADD
                2:SUBRACTION
                3:DIVISION
                4:MULTIPLICATION
                5:MODULUS''')
ch=int(input('Enter operation(1,2,3,4)'))
if ch==1:
    add(a,b)
elif ch==2:
    sub(a,b)
elif ch==3:
    div(a,b)
elif ch==4:
    mult(a,b)
else:
    mod(a,b)