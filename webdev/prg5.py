#PRG 5
lst1=[]
lst2=[]
n=int(input('Enter the total no of students: '))
for i in range(n):
    n1=input('Male or Female(M/F): ')
    if n1 in'Mm':
        h1=float(input('Enter the height: '))
        lst1.append(h1)
    elif n1 in 'Ff':
        h2=float(input('Enter the height: '))
        lst2.append(h2)
    else:
        print('Invalid choice(M/F)')
print('Avg height in boys' ,sum(lst1)/len(lst1))
print('Avg height in girls ',sum(lst2)/len(lst2))



