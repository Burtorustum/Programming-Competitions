from helper import *
input = getData(4).splitlines()

c1, c2 = 0, 0
for line in input:
    line = line.split(',')    
    range1 = list(map(int, line[0].split('-')))
    range2 = list(map(int, line[1].split('-')))
    
    if (range1[0] <= range2[0] and range1[1] >= range2[1]) or (range2[0] <= range1[0] and range2[1] >= range1[1]):
        c1 += 1
        c2 += 1
    elif (range1[0] >= range2[0] and range1[0] <= range2[1]) or (range1[1] >= range2[0] and range1[1] <= range2[1]):
        c2 += 1

print("pt1: ", c1)
print("pt2: ", c2)