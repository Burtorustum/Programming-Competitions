def palindromic(i):
    for c in range(len(i)//2):
        if i[c] != i[-1-c]:
            return False
    return True

c = 0
for i in range(0, 1000000):
    if palindromic(str(i)) and palindromic(str(bin(i))[2:]):
        c += i

print('sum', c)