# блок a
A = int(input("Введите число A: "))
B = int(input("Введите число B: "))

def print_numbers(a, b):
    if a == b:
        print(a)
    elif a < b:
        print(a)
        print_numbers(a + 1, b)
    else:
        print(a)
        print_numbers(a - 1, b)

print_numbers(A, B)

# блок б
def max_number():
    n = int(input("Введите число: "))
    if n == 0:
        return 0
    max_in_rest = max_number()
    return max(n, max_in_rest)

print("Наибольшее число в последовательности:", max_number())