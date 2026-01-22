def add(a,b):
    print(a+b)
def sub(a,b):
    print(a-b)
def multi(a,b):
    print(a*b)
def div(a,b):
    print(a/b)
def mod(a,b):
    print(a%b)
a=float(input('Enter the first number: '))
b=float(input('Enter second number: '))
ch=int(input('''OPERATIONS AVAILABLE
                     1:ADDITION
                     2:SUBSTRACTION
                     3:MULTIPLICATION
                     4:DIVISION 
                     5:REMAINDER
             Enter operation here (1,2,3,4,5): '''))
if ch==1:
    add(a,b)
elif ch==2:
    sub(a,b)
elif ch==3:
    multi(a,b)
elif ch==4:
    if b==0:
        print('division by zero not possible')
    div(a,b)
elif ch==5:
    mod(a,b)
else:
    print('Invalid choice')
    