def check(y):
    if(y % 4 == 0 and y % 100!=0) or y % 400 == 0:
        return ('Да')
    else:
        return ('Нет')
    
y = int(input('Введите год, чтобы узнать високосный он или нет: '))

print (check(y))