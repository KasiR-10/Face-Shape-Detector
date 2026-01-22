n=int(input('Enter no of elements in a the list'))
lst=[]
for i in range(0,n):
    x=int(input('Enter element'))
    lst.append(x)
lst[0],lst[-1]=lst[-1],lst[0]
print(lst)
