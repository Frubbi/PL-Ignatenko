def count():
    count = 0
    pred = int(input("Введите число(последнее 0): "))
    while True:
        num = int(input("Введите число(последнее 0): "))
        if num == 0:
            break
        if num > pred:
            count += 1
        pred = num
    return count
result = count()
print(f"Количество элементов, больших предыдущего: {result}")
