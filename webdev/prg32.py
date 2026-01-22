
n=int(input('Enter no of elements in a the list'))
lst=[]
for i in range(0,n):
    x=int(input('Enter element'))
    lst.append(x)
y=list(set(lst))
y.sort(reverse=True)
print(y[1])