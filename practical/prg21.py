n=int(input('Enter the total elements of the list'))
lst=[]
for i in range(0,n):
    x=int(input('Enter element'))
    lst.append(x)
lst.sort()
n=len(lst)
if n%2==1:
    print('Median = ',lst[n//2])
else:
    print((lst[(n//2)-1]+lst[n//2])/2)
