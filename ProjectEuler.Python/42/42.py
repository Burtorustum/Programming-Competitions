from pathlib import Path
import string

mod_path = Path(__file__).parent
relative_path = './input.txt'
src_path = (mod_path / relative_path).resolve()
file = open(src_path, "r")

def triangle_num(n):
    return int(n * (n + 1) / 2)
triangles = [triangle_num(i) for i in range(1000)]


input = file.read().replace('"', '').strip().lower().split(',')
converted = [[string.ascii_lowercase.index(c)+1 for c in x] for x in input]
word_sums = [sum(x) for x in converted]

print(input[0:3])
print(converted[0:3])
print(word_sums[0:3])

count = 0
for sum in word_sums:
    if triangles.count(sum) > 0:
        count += 1
print(count)