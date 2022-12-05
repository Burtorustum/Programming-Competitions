import re
from helper import *
input = getData(4).splitlines()

input = [list(map(int, re.split(',|-', x))) for x in input]
c1, c2 = 0, 0
for line in input:
    if (line[0] <= line[2] and line[1] >= line[3]) or (line[2] <= line[0] and line[3] >= line[1]):
        c1 += 1
        c2 += 1
    elif (line[0] >= line[2] and line[0] <= line[3]) or (line[1] >= line[2] and line[1] <= line[3]):
        c2 += 1

print("pt1: ", c1)
print("pt2: ", c2)