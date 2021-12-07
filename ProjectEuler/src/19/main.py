monthdays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]

def main():
    year = 1901
    month = 0
    day = 1
    name = 2

    count = 0
    while year < 2001:
        day += 1
        name += 1
        if day > monthdays[month] and not (day == 28 and month == 1 and year % 4 == 0):
            if month == 11:
                year += 1
            month = (month + 1) % 12
            day = 1

        if day == 1 and name % 7 == 0:
            count += 1

    print(count)

if __name__ == '__main__':
    main()
