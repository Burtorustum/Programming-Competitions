from helper import *
input = getData(6)

target = 14
p = target - 1

prev = []
for i in range(p):
    prev.append(input[i])

count = 0
while count != target:
    prev.append(input[p])
    p += 1
    
    count = 0
    for c in prev:
        count += prev.count(c)
    prev.pop(0)

print(p)