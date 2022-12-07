class Dir:
    def __init__(self, name):
        self.name = name
        self.folder = []
        self.size = 0

    def print(self):
        print('dir', self.name, self.size)
        for f in self.folder:
            f.print()
    
    def fill_size(self):
        i = 0
        for f in self.folder:
            i += f.fill_size()
        self.size = i
        return i
    
    def findAllSize(self, max_size, arr):
        if self.size <= max_size:
            arr.append(self.size)
        for f in self.folder:
            f.findAllSize(max_size, arr)
    
    def get_all(self, arr):
        arr.append(self)
        for f in self.folder:
            f.get_all(arr)

class File:
    def __init__(self, size, name):
        self.size = size
        self.name = name

    def print(self):
        print('file', self.size, self.name)

    def fill_size(self):
        return self.size
    
    def findAllSize(self, max_size, arr):
        return

    def get_all(self, arr):
        return

def create_structure(input):
    root = Dir('/')
    cur = root
    above = []
    for line in input:
        line = line.split(' ')
        
        if line[1] == 'cd':
            if (line[2] == '..'):
                cur = above.pop()
            elif (line[2] == '/'):
                cur = root
                above.clear()
            else:
                above.append(cur)
                for f in cur.folder:
                    if f.name == line[2]:
                        cur = f

        elif line[1] != 'ls':
            if line[0] == 'dir':
                cur.folder.append(Dir(line[1]))
            else:
                cur.folder.append(File(int(line[0]), line[1]))
    return root

from helper import *
input = getData(7).splitlines()
#input = getTestData(7).splitlines()

structure = create_structure(input)
structure.fill_size()

arr = []
max_size = 100000
structure.findAllSize(max_size, arr)
print(sum(arr))

disk_space = 70000000
min_free = 30000000
cur_free = disk_space - structure.size
to_free = min_free - cur_free

files = []
structure.get_all(files)
best = structure

for f in files:
    if isinstance(f, Dir) and f.size >= to_free and f.size < best.size:
            best = f

print(best.name, best.size)