import math

x = 12
sum = 0
for i in range(1000000):
    x += 1
    str_x = str(x)
    factorial_sum = 0
    for c in str_x:
        factorial_sum += math.factorial(int(c))
    if factorial_sum == x:
        print(x)
        sum += x

print(sum)