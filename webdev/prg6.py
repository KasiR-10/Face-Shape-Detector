#PRG 6
n=float(input('Enter the increment percentage'))
lst=[]
amt=int(input('Enter the amount to be deposited'))
for i in range(10):
    x=(n/100)*amt  + amt
    lst.append(x)
print('The total savings after 10 years is : ',sum(lst))