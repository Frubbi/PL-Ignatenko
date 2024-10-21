def change(input_str):
    n = len(input_str)
    first_half = input_str[:n // 2].replace('п', '*')
    second_half = input_str[n // 2:]
    input_str = first_half + second_half
    return input_str

input_str = input("Введите строку: ")
result = change(input_str)
print("Преобразованная строка:", result)