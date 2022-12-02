from helper import getData
input = getData(2).splitlines()

rock = 1
paper = 2
scissors = 3
lose = 0
win = 6
tie = 3

sum1 = 0
sum2 = 0
for line in input:
    chars = line.split(' ')
    if chars == ['A', 'X']:
        sum1 += rock + tie
        sum2 += scissors + lose
    elif chars == ['A', 'Y']:
        sum1 += paper + win
        sum2 += rock + tie
    elif chars == ['A', 'Z']:
        sum1 += scissors + lose
        sum2 += paper + win
    elif chars == ['B', 'X']:
        sum1 += rock + lose
        sum2 += rock + lose
    elif chars == ['B', 'Y']:
        sum1 += paper + tie
        sum2 += paper + tie
    elif chars == ['B', 'Z']:
        sum1 += scissors + win
        sum2 += scissors + win
    elif chars == ['C', 'X']:
        sum1 += rock + win
        sum2 += paper + lose
    elif chars == ['C', 'Y']:
        sum1 += paper + lose
        sum2 += scissors + tie
    elif chars == ['C', 'Z']:
        sum1 += scissors + tie
        sum2 += rock + win

print("pt1:")
print(sum1)
print("pt2:")
print(sum2)