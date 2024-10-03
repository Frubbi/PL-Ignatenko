n=int(input('Введите число, чтобы рассчитать его факториал: '))

def factorial(n):
    result = 1
    for i in range(1, n + 1):
        result *= i
    return result

print(factorial(n))
