a=float(input('Enter the temperature(C/F): '))
ch=input('Temperature reading in (C/F): ')
if ch in 'Cc':
    temp1=(9/5)*a + 32
    print('Temperature in Farenheit: ',temp1)
else:
    temp2=(a-32)*5/9
    print('Temperature in Celcius: ',temp2)