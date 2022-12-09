from helper import *

def move_t(h, t):
    x_diff = h[0] - t[0]
    y_diff = h[1] - t[1]

    return abs(x_diff) > 1 or abs(y_diff) > 1
def new_pos(h, t):
    if not move_t(h, t):
        return t
    if h[0] > t[0] and h[1] > t[1]:
        return [t[0] + 1, t[1] + 1]
    if h[0] == t[0] and h[1] > t[1]:
        return [t[0], t[1] + 1]
    if h[0] < t[0] and h[1] > t[1]:
        return [t[0] - 1, t[1] + 1]
    if h[0] > t[0] and h[1] == t[1]:
        return [t[0] + 1, t[1]]
    if h[0] < t[0] and h[1] == t[1]:
        return [t[0] - 1, t[1]]
    if h[0] > t[0] and h[1] < t[1]:
        return [t[0] + 1, t[1] - 1]
    if h[0] == t[0] and h[1] < t[1]:
        return [t[0], t[1] - 1]
    else:
        return [t[0] - 1, t[1] - 1]

data = getData(9).splitlines()
#data = getTestData(9).splitlines()

rope = [[0, 0]] * 10
tail_pos = []

for line in data:
    line = line.split(' ')
    direction = line[0]
    amt = int(line[1])

    for i in range(amt):
        if direction == 'R':
            rope[-1][0] += 1
        elif direction == 'L':
            rope[-1][0] -= 1
        elif direction == 'U':
            rope[-1][1] += 1
        else:
            rope[-1][1] -= 1

        for s in range(len(rope) - 2, -1, -1):
            rope[s] = new_pos(rope[s+1], rope[s]).copy()

        if tail_pos.count((rope[0][0], rope[0][1])) == 0:
            tail_pos.append((rope[0][0], rope[0][1]))

print(len(tail_pos))
