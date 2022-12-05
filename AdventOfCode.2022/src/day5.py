import re
from helper import *
input = getData(5).splitlines()
#input = getTestData(5).splitlines()

graph, commands = [], []
for line in input:
    if line.count('[') > 0:
        line = line.replace('    ', '#')
        line = re.sub('\]|\[| ', "", line)
        graph.append(line)
    if line.count('move') > 0:
        commands.append(line.split())
#print(graph)
#print(commands)

stacks = []
for i in range(len(graph[0])):
    stacks.append([])

for line in graph:
    for i in range(len(line)):
        if line[i] != '#':
            stacks[i].append(line[i])
#print(stacks)

for com in commands:
    num = int(com[1])
    start = int(com[3]) - 1
    dest = int(com[5]) - 1
    
    x = 0
    for i in range(num):
        stacks[dest].insert(x, stacks[start].pop(0))
        x+=1
#print(stacks)

for l in stacks:
    print(l[0], end="")