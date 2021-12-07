def main():
    count = 0
    for i in range(1, 10000):
        if is_lychrel(i): count += 1
    print(count)

def is_lychrel(n):
    n_rev = int(rev_str(str(n)))
    for i in range(50):
        n = n + n_rev
        n_rev = int(rev_str(str(n)))
        if is_palindrome(n): return False
    return True

def rev_str(s):
    return s[::-1]

def is_palindrome(x):
    x = str(x)
    return x == rev_str(x)

if __name__ == '__main__':
    main()
