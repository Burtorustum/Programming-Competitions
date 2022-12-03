import string
from helper import *
input = getData(3).splitlines()
test_input = getTestData(3).splitlines()

sum = 0
for line in input:
    one, two = line[:len(line)//2], line[len(line)//2:]
    res = ""
    for c in one:
        if (two.count(c) > 0 and res.count(c) < 1):
            res += c
    for c in res:
        sum += string.ascii_letters.index(c) + 1 

print("pt1:")
print(sum)

sum = 0
while len(input) != 0:
    one, two, three = input[0], input[1], input[2]
    del(input[:3])

    res = ""
    for c in one:
        if (two.count(c) > 0 and three.count(c) > 0 and res.count(c) < 1):
            res += c
    for c in res:
        sum += string.ascii_letters.index(c) + 1 

print("pt2:")
print(sum)