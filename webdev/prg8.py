#PRG 8
b=int(input('How many students'))
n=int(input('Enter the total working days'))
for i in range(b):
    a=input('Enter student name')
    x=int(input('Enter the total days attended by student'))
    y=(x/n)*100
    if y>=75:
        print(a,'Has Attendance above 75%')
    else:
        print(a,'Has Attendance below 75%')
    