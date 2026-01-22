n=int(input('Enter no of elements in a the list'))
lst=[]
a,b=0,0
for i in range(0,n):
    x=int(input('Enter element'))
    lst.append(x)
for i in lst:
    if i%2==0:
        a+=1
    else:
        b+=1
print('EVEN COUNT',a)
print('ODD COUNT',b)