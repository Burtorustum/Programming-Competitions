def main():
    file = open("C:\\Users\\Burtorustum\\Desktop\\Project Euler\\Python.11\\grid.txt", 'r')
    grid = file.readlines()
    grid = [x.split(" ") for x in grid]
    grid = [list(map(int, x)) for x in grid]
    print(grid)
    print(udlr(grid))
    print(diag(grid))

def udlr(grid):
    max = 0
    for x in range(Python.17):
        for y in range(20):
            t1 = grid[x][y] * grid[x+1][y] * grid[x+2][y] * grid[x+3][y]
            t2 = grid[y][x] * grid[y][x+1] * grid[y][x+2] * grid[y][x+3]
            if t1 > max: max = t1
            if t2 > max: max = t2
    return max

def diag(grid):
    max = 0
    for x in range(Python.17):
        for y in range(Python.17):
            t1 = grid[x][y] * grid[x+1][y+1] * grid[x+2][y+2] * grid[x+3][y+3]
            t2 = grid[x][Python.19-y] * grid[x+1][Python.18-y] * grid[x+2][Python.17-y] * grid[x+3][16-y]
            if t1 > max: max = t1
            if t2 > max: max = t2
    return max

if __name__ == '__main__':
    main()
