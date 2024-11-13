import math
# первый способ
def square_triangle(a, b):
    return 0.5 * a * b

def square_rectangle(a, b):
    return a * b

def calculate_quadrilateral_area(X, Y, Z, T):
    square1 = square_triangle(X, Y)
    square2 = square_rectangle(Z, T)
    return square1 + square2

X = 3
Y = 4
Z = 5
T = 6

area = calculate_quadrilateral_area(X, Y, Z, T)
print("Площадь четырехугольника: ", area)

# второй способ
def square_tr(a, b):
    return 0.5 * a * b

def tr_f(a, b):
    return math.sqrt(a**2+b**2)

def square_tr_2(a, b, c):
    return math.sqrt(a+b+c)

def calculate_quadrilateral_area_2(X_2, Y_2, Z_2, T_2):
    square1 = square_tr(X_2, Y_2)
    a = tr_f(X_2, Y_2)
    square2 = square_tr_2(a, Z_2, T_2)
    return square1 + square2

X_2 = 3
Y_2 = 4
Z_2 = 5
T_2 = 6

area = calculate_quadrilateral_area_2(X_2, Y_2, Z_2, T_2)
print("Площадь четырехугольника: ", area)

def s(n):
    m = ""
    while n != 0:
        m = str(n%8) + m
        n //= 8
    return m

a = int(input("Введите неотрицательное целое чсисло: "))
print("Преобразованное число: ", s(a))