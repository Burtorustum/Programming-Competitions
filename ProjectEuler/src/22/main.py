alphabet = ['', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
def main():
    file = open("C:\\Users\\Burtorustum\\Desktop\\Project Euler\\22\\names.txt", 'r')
    names = file.read().split(',')
    names = [x.replace('\"', '') for x in names]
    names.sort()
    names.insert(0, '')
    scores = [score(x) * names.index(x) for x in names]
    print(sum(scores))

def score(text):
    sum = 0
    for char in text:
        sum += alphabet.index(char)
    return sum

if __name__ == '__main__':
    main()
