from helper import getData
input = getData(2).splitlines()

rockScore = 1
paperScore = 2
scissorsScore = 3
lose = 0
win = 6
tie = 3

sum1 = 0
sum2 = 0
for line in input:
    chars = line.split(' ')
    if chars == ['A', 'X']:
        sum1 += tie + rockScore
        sum2 += scissorsScore + lose
    elif chars == ['A', 'Y']:
        sum1 += paperScore + win
        sum2 += rockScore + tie
    elif chars == ['A', 'Z']:
        sum1 += scissorsScore + lose
        sum2 += paperScore + win
    elif chars == ['B', 'X']:
        sum1 += rockScore + lose
        sum2 += rockScore + lose
    elif chars == ['B', 'Y']:
        sum1 += paperScore + tie
        sum2 += paperScore + tie
    elif chars == ['B', 'Z']:
        sum1 += scissorsScore + win
        sum2 += scissorsScore + win
    elif chars == ['C', 'X']:
        sum1 += rockScore + win
        sum2 += paperScore + lose
    elif chars == ['C', 'Y']:
        sum1 += paperScore + lose
        sum2 += scissorsScore + tie
    elif chars == ['C', 'Z']:
        sum1 += scissorsScore + tie
        sum2 += rockScore + win

print("pt1:")
print(sum1)
print("pt2:")
print(sum2)