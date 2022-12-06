set = set()

for a in range(2, 101):
    for b in range(2, 101):
        set.add(a**b)
        
print(len(set))