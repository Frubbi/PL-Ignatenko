import random
mas = []
mas = [random.randint(1,100) for i in range(1,5)]
print("Начальный массив:", mas)
def sum(mas):
    sum = 0
    for i in mas:
        if(i%2) == 0:
            sum +=i
    return sum
def pr(mas):
    pr = 1
    for i in mas:
        if(i%2) !=0:
            pr *=i 
    return pr
def swap(mas):
    min_idx = mas.index(min(mas))
    max_idx = mas.index(max(mas))
    mas[min_idx], mas[max_idx] = mas[max_idx], mas[min_idx]
    return mas

result_sum = sum(mas)
print("Сумма элементов с чётными номерами:", result_sum)
result_pr = pr(mas)
print("Произведение элементов с нечётными номерами:", result_pr)
mas_swapped = swap(mas)
print("Изменённый массив:", mas_swapped)

