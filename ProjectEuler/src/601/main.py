def main():
    print(P(3, 14))
    print(P(6, 10**6))
    a = 0
    for i in range(31):
        if i == 0: continue
        a += P(i, 4 ** i)
        print(i)
    print(a)


def streakNum(n, k, s):
    if k > s + 1:
        return k
    if k == 1 and n % 2 == 0:
        return 1
    if n % k == 0:
        return streak(n+1, k+1, s)
    return k-1

def streak(n, k, s):
    if k > s + 1:
        return False
    if n % k == 0:
        return streak(n+1, k+1, s)
    return k-1 == s

def P(s, N):
    n = 0
    for i in range(N):
        if i == 0 or i == 1:
            continue
        if i % 2 == 0 and s != 1:
            continue
        if streak(i, 1, s):
            n += 1
    return n


if __name__ == '__main__':
    main()
