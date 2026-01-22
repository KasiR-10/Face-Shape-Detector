#PRG 9
def track_cal():
 
    calories = []
    for i in range(1,8):
        cal = float(input(f"Enter calorie intake for day {i}: "))
        calories.append(cal)

   
    total_cal = sum(calories)
    avg_calories = total_cal / len(calories)

   
    days_exceeded = []
    for i in range(len(calories)):
        if calories[i] > avg_calories:
            days_exceeded.append(i + 1) 

    
    print(f"Total calorie intake for the week: {total_cal:}")
    print(f"Average daily calorie intake: {avg_calories: }")
    print(f"Days exceeding average intake: {days_exceeded}")
track_cal()
