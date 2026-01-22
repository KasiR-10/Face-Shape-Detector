mark=float(input('Enter the marks'))
tmark=int(input('Enter total marks'))
per=(mark/tmark)*100
if per>90:
    print('A+')
elif per>80:
    print('A')
elif per>70:
    print('B')
elif per>60:
    print('c')
else:
    print('Fail')