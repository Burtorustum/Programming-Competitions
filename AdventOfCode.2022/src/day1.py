from helper import getData

input = getData(1)

maxsum = []
sum = 0
for line in input:
    if line != '':
        sum += int(line)
    else:
        maxsum.append(sum)
        sum=0

maxsum.sort()

print("pt1:")
print(maxsum[-1])
print("pt2:")
print(maxsum[-1] + maxsum[-2] + maxsum[-3])