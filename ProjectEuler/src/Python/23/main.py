from math import sqrt

def main():
    #num = sum([x for x in range(24)])
    abunds = genAbunds()
    print(abunds)
    print(len(abunds))
    #print(abunds)
    #for i in range(25, 20161):
    #    num += i if not canSum(i, abunds) else 0
    #    print(i)
    sums = set(genSums(abunds))
    allNums = set([x for x in range(28124)])
    notSums = list(allNums - sums)
    notSums.sort()
    print(notSums)
    print(sum(notSums))

#stupid
def canSum(i, nums):
    if i < nums[0]: return False
    for x in range(len(nums)):
        for y in range(x, len(nums)):
            sum = nums[x] + nums[y]
            if sum == i: return True
            elif sum > i: break
        if nums[x]*2 > i: return False

    return False

def genSums(nums):
    n = []
    for x in range(len(nums)):
        for y in range(x, len(nums)):
            n.append(nums[x] + nums[y])

    return n

def genAbunds():
    abundants = []
    for i in range(Python.11, 28124):
        if d(i) > i:
            abundants.append(i)
    return abundants

def d(n):
    sum = 1
    for i in range(2, int(sqrt(n))+1):
        if n % i == 0:
            sum += i
            if n/i != i: sum += n / i
    return sum

if __name__ == '__main__':
    main()
