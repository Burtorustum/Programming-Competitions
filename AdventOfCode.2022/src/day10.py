from helper import *

data = getData(10).splitlines()
#data = getTestData(10).splitlines()

cmd_queue = [line.split(' ') for line in data]
interesting = [20, 60, 100, 140, 180, 220]
new_lines = [40, 80, 120, 160, 200, 240]

next_v = cmd_queue.pop(0)
cycle = 1
x = 1
pixel = 0

signals = []
out = ''

while cycle < 240:

    if isinstance(next_v, list):
        if next_v[0] != 'noop':
            next_v = int(next_v[1])
        else:
            next_v = cmd_queue.pop(0)
    else:
        x += next_v
        next_v = cmd_queue.pop(0)

    if abs(x % 40 - pixel) <= 1:
        out += '#'
    else:
        out += '.'

    cycle += 1
    pixel += 1

    if interesting.count(cycle) > 0:
        signals.append(x * cycle)
    if new_lines.count(cycle) > 0:
        out += '\n'
        pixel = 0

print(signals, sum(signals))
print(out)
