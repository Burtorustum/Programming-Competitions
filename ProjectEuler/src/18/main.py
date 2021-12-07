def main():
    #file = open("C:\\Users\\Burtorustum\\Desktop\\Project Euler\\18\\triangle.txt", 'r')
    file = open("C:\\Users\\Burtorustum\\Desktop\\Project Euler\\18\\smalltriangle.txt", 'r')
    triangle = file.readlines()
    triangle = [x.split(" ") for x in triangle]
    triangle = [list(map(int, x)) for x in triangle]
    print(triangle)



if __name__ == '__main__':
    main()
