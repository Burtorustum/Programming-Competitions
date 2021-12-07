from math import sqrt

def main():
    amicables = []
    for i in range(10000):
        if i == 0 or i == 1: continue
        am = d(i)
        if d(am) == i and am != i and i not in amicables:
            amicables.append(i)
            amicables.append(am)
    print(amicables)
    print(sum(amicables))

def d(n):
    sum = 1
    for i in range(2, int(sqrt(n))+1):
        if n % i == 0:
            sum += i
            sum += n / i
    return sum


if __name__ == '__main__':
    main()
