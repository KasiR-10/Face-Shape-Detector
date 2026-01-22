num=int(input('Enter the number'))
if num<10:
    lnum=num
    fnum=num
else:
    lnum=num%10
    while num>=10:
        fnum=num//10
        num=num//10
    print(fnum,lnum)