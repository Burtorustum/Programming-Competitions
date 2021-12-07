from sys import version
def main():
    print(version)
    count = 1
    n = 10
    while count < 31:
        n += 1
        p = s = sum(map(int, str(n)))
        power = 1
        while n % p == 0 and p < n and s != 1:
            p *= s
            power += 1
            if p == n:
                print([count, n, power, s])
                count += 1

def backwards():
    results = [0]
    n = 2
    while len(results) < 40:
        n += 1
        p = n
        for i in range(10):
            p *= n
            if sum(map(int, str(p))) == n:
                results.append(p)
        print(n)
    results.sort()
    print(results)
    print(results[30])

if __name__ == '__main__':
    backwards()
    #main()
