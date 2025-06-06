# 1 
import numpy as np

m = np.loadtxt("python/z_10_arh/Игнатенко_Дарья_Даниловна_У-243_vvod.txt", dtype='i', delimiter=',')

with open('python/z_10_arh/Игнатенко_Дарья_Даниловна_У-243_vvod.txt', 'r') as file:
    n = sum(1 for line in file)
 
for i in range(n):
    for j in range(i,n):
        m[j][i] = m[i][j]
for row in m:
    print( row, sep='\t' )

# 2
m = np.loadtxt("python/z_10_arh/Игнатенко_Дарья_Даниловна_У-243_vvod.txt", dtype='i', delimiter=',')

matrix = np.array(m)

diagonal_elements = np.diagonal(matrix)

trace = np.sum(diagonal_elements)

for i in range(len(matrix)):
    if i % 2 == 0:  
        matrix[i] = matrix[i] / trace

print("Диагональные элементы матрицы:", diagonal_elements)
print("След матрицы:", trace)
print("Преобразованная матрица:")
print(matrix)

d_e =str(diagonal_elements)
t =str(trace)
mat =str(matrix)
with open("python/z_10_arh/Игнатенко_Дарья_Даниловна_У-243_vivod.txt", "w") as file:
    file.write(d_e)
    file.write("\n")
    file.write(t)
    file.write("\n")
    file.write(mat)