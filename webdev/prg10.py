#PRG 10
def calc_increment(ratings):
    total_increment = 0
    for rating in ratings:
        if rating >= 9:
            total_increment +=10
        elif rating >= 7:
            total_increment +=7
        elif rating >= 5:
            total_increment +=5
    return total_increment


num_emp = int(input("Enter the number of employees: "))

for i in range(num_emp):
    name = input("Enter the name of employee ")
    ratings = []
    
    print(f"Enter performance ratings for {name} over 12 months:")
    for month in range(1,13):
        rating = float(input(f"Month {month} rating (0-10): "))
        ratings.append(rating)

    total_increment = calc_increment(ratings)
    print(f"{name}'s total annual increment: {total_increment}%")