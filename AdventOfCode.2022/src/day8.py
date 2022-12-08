from helper import *


def is_visible(x, y, map):
    if x == 0 or x == len(map) or y == 0 or y == len(map[0]):
        return True
    return left(x, y, map) or right(x, y, map) or up(x, y, map) or down(x, y, map)


def left(x, y, map):
    for i in range(0, x):
        if map[i][y] >= map[x][y]:
            return False
    return True


def right(x, y, map):
    for i in range(0, y):
        if map[x][i] >= map[x][y]:
            return False
    return True


def up(x, y, map):
    for i in range(x + 1, len(map[0])):
        if map[i][y] >= map[x][y]:
            return False
    return True


def down(x, y, map):
    for i in range(y + 1, len(map[0])):
        if map[x][i] >= map[x][y]:
            return False
    return True


def sleft(x, y, map):
    count = 0
    i = x - 1
    while i >= 0:
        if map[i][y] < map[x][y]:
            count += 1
        if map[i][y] >= map[x][y]:
            return count + 1
        i -= 1
    return count


def sright(x, y, map):
    count = 0
    i = y - 1
    while i >= 0:
        if map[x][i] < map[x][y]:
            count += 1
        if map[x][i] >= map[x][y]:
            return count + 1
        i -= 1
    return count


def sup(x, y, map):
    count = 0
    i = x + 1
    while i < len(map):
        if map[i][y] < map[x][y]:
            count += 1
        if map[i][y] >= map[x][y]:
            return count + 1
        i += 1
    return count


def sdown(x, y, map):
    count = 0
    i = y + 1
    while i < len(map[0]):
        if map[x][i] < map[x][y]:
            count += 1
        if map[x][i] >= map[x][y]:
            return count + 1
        i += 1
    return count


def scenic(x, y, map):
    return sup(x, y, map) * sdown(x, y, map) * sright(x, y, map) * sleft(x, y, map)


data = getData(8).splitlines()
# data = getTestData(8).splitlines()

topography = [[int(x) for x in line] for line in data]

vis = []
best = 0
for i in range(len(data)):
    for j in range(len(data[i])):
        if is_visible(i, j, topography):
            vis.append(topography[i][j])
        s = scenic(i, j, topography)
        if s > best:
            best = s

print(len(vis))
print(best)