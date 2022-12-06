import math

x = 10
sum = 0
for i in range(1000000):
    x += 1
    str_x = str(x)
    pow_sum = 0
    for c in str_x:
        pow_sum += math.pow(int(c), 5)
    if pow_sum == x:
        print(x)
        sum += x

print(sum)