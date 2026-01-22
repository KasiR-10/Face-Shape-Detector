#PRG 4
lst=[]
print('Enter 0 to stop giving input')
while True:
    age=int(input('enter the age '))
    lst.append(age)
    if age==0:
        break
print('Average of the ages is',sum(lst)/len(lst))

