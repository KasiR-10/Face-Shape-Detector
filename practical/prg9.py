a=float(input('Enter the number'))
b=float(input('Enter the number'))
c=float(input('Enter the number'))
if a<b and a<c:
    print(a,'greater')
elif b<a and b<c:
    print(b,'greater')
elif c<a and c<b:
    print(c,'greater')
else:
    pass