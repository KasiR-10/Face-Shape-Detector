n=int(input('Enter total elements in list'))
y=(0,)
for i in range(0,n):
    a=int(input('Enter elements'))
    y+=(a,)
print(sum(y))
