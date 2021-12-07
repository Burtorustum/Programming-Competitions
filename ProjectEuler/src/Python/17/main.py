nums = ['', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine', 'ten', 'eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen']
tens = ['', 'ten', 'twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety']

def main():
    sum = 0
    for i in range(1000):
        if i == 0: continue
        sum += count(i)
    sum += len('onethousand')
    print(sum)

def count(i):
    sum = 0
    count = 0
    flag = False
    while i > 0:
        n = i % 10
        i = int(i / 10)
        count += 1
        if n == 0: continue
        if count == 1:
            flag = True
            if i % 10 == 1:
                sum += len(nums[10 + n])
                i = int(i / 10)
                count += 1
            else:
                sum += len(nums[n])
        elif count == 2:
            flag = True
            sum += len(tens[n])
        elif count == 3:
            sum += len(nums[n]) + len('hundred')
            if flag: sum += 3
    return sum

if __name__ == '__main__':
    main()
