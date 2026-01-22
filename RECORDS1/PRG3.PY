a=float(input('Enter first number: '))
b=float(input('Enter second number: '))
c=int(input('Enter third number'))
if a>b and a>c:
    print('Greatest number: ',a)
elif b>c and b>a:
    print('Greatest number found: ',b)
else:
    print('Greatest number: ',c)