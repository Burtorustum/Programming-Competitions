from math import sqrt

def main():
    curmax = 9
    D = 0
    for d in range(5, 1001):
        if sqrt(d) % 1 == 0: continue
        #y = min_solution_convergent(d)
        y = min_solution(d, curmax)
        if y > curmax:
            #min_solution
            curmax = y
            D = d
        print(d)
    print(D)

def diophantine(x, y, D):
    return x*x - D*y*y == 1

def min_solution(D, curmax):
    x = int(sqrt(D))
    while True:
        for y in range(1, x):
            res = diophantine(x, y, D)
            if res < 1: break
            if res == 1: return x
        x += 1
        if x > curmax: return x

def min_solution_convergent(D):
    i = 0
    while True:
        x = p(i, D)
        y = q(i, D)
        if diophantine(x, y, D): return x
        i += 1

def p(n, D):
    if n == 0: return a(0, D)
    if n == 1: return a(0, D)*a(1, D) + 1
    return a(n, D)*p(n-1, D) + p(n-2, D)

def q(n, D):
    if n==0: return 1
    if n==1: return a(1, D)
    return a(n, D)*q(n-1, D) + q(n-2, D)

def a(n, D):
    if n == 0: return int(sqrt(D))
    return int((a(0, D) + P(n, D))/Q(n, D))

def P(n, D):
    if n==0: return 0
    if n==1: return a(0, D)
    return a(n-1, D)*Q(n-1, D)-P(n-1, D)

def Q(n, D):
    if n==0: return 1
    if n==1: return D-a(0, D)**2
    return (D - P(n, D)**2)/ Q(n-1, D)

















if __name__ == '__main__':
    main()
