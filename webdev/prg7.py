#PRG 7
def calculate_bill(units):
    if units <= 100:
        return units * 0.5
    elif units <= 300:
        return 100 * 0.5 + (units - 100) * 0.75
    else:
        return 100 * 0.5 + 200 * 0.75 + (units - 300) * 1.0


num_tenants = int(input("Number of tenants: "))
total_bill = 0

for i in range(1,num_tenants):
    units = float(input(f"Units for tenant {i}: "))
    bill = calculate_bill(units)
    total_bill += bill
    print(f"Tenant {i } bill: ${bill:}")

print(f"Total bill: ${total_bill:}")