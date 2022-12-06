import re
from helper import *
input = getData(6)

target = 4
p = target - 1

prev = []
for i in range(p):
    prev.append(input[i])
    
while True:
    prev.append(input[p])
    p += 1
    count = 0
    for c in prev:
        if prev.count(c) == 1:
            count += 1
    if (count == target):
        break
    prev.pop(0)
    
print(p)