
n=int(input('Enter no of elements in a the list'))
lst=[]
for i in range(0,n):
    x=int(input('Enter element'))
    lst.append(x)
print('sum is ',sum(lst))
print('AVG is ',sum(lst)/len(lst))