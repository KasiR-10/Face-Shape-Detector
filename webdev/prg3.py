#PRG 3
rainfall_data = {
    2003: 1200,
    2004: 900,
    2005: 1100,
    2006: 1300,
    2007: 950,
    2008: 1150,
    2009: 800,
    2010: 1050,
    2011: 990,
    2012: 1250,
    2013: 870,
    2014: 1130,
    2015: 1000,
    2016: 1020,
    2017: 1180,
    2018: 1290,
    2019: 850,
    2020: 1105,
    2021: 1400,
    2022: 1155,
    2023: 1080
}

total_rainfall = sum(rainfall_data.values())
average_rainfall = total_rainfall / len(rainfall_data)


max_year = max(rainfall_data, key=rainfall_data.get)
min_year = min(rainfall_data, key=rainfall_data.get)


print(f"Average annual rainfall: {average_rainfall:} ")
print(f"Max rainfall: {max_year} [{rainfall_data[max_year]}] ")
print(f"Min rainfall: {min_year} [{rainfall_data[min_year]}]")