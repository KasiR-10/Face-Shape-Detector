#PRG 1
n=int(input('Enter total no of trips : '))
lst=[]
for i in range(n):
    dis=float(input('Enter the total distance'))
    fuel=float(input('Enter the fuel consumed'))
    print('Avg fuel efficency of the trip ',dis/fuel)
    lst.append(fuel)
print('Avg fuel efficiency of n trips ',sum(lst)/len(lst))
