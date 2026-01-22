n=int(input('Enter no of elements in a the list'))
lst=[]
for i in range(0,n):
    x=int(input('Enter element'))
    lst.append(x)
y=int(input('Enter the element to be checked'))
print('No of occurence',lst.count(y))