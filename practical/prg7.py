yr=int(input('Enter the year'))
if (yr%4==0 and yr%100!=0) or yr%400==0:
    print('Leap year')
else:
    print('not leap yaer')