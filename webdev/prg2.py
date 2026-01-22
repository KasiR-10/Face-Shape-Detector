#PRG 2
lst=[]
n=int(input('Enter the total temp readings : '))
for i in range(0,n):
    temp=int(input('enter temp : '))
    lst.append(temp)
print('Max temp recorded',max(lst))