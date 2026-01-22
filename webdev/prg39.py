
a=input('Enter string')
y = []  # Create an empty list to store the modified words
word=a.split()    
for i in word:
    if len(i) > 1:
           
        x = i[0].upper() + i[1:-1] + i[-1].upper()
    else:
           
        x = i.upper()
        
    y.append(x)  # Add the modified word to the result list
    
print(' '.join(y))  # Join the words back into a single string





