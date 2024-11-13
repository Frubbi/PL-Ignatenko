# 1 
n = int( input( 'Размер матрицы: ' ) )
k = (n*n - n)//2 + n
print( f'Введите {k} элементов матрицы: ' )
m = []
for i in range(n):
    m.append( [0]*n )
    for j in range(i,n):
        m[i][j] = int(input( '-> ' ) )
 
for i in range(n):
    for j in range(i,n):
        m[j][i] = m[i][j]
for row in m:
    print( row, sep='\t' )

# 2
import numpy as np

matrix = np.array([[1, 2, 3],
                   [4, 5, 6],
                   [7, 8, 9]])

diagonal_elements = np.diagonal(matrix)

trace = np.sum(diagonal_elements)

for i in range(len(matrix)):
    if i % 2 == 0:  
        matrix[i] = matrix[i] / trace

print("Диагональные элементы матрицы:", diagonal_elements)
print("След матрицы:", trace)
print("Преобразованная матрица:")
print(matrix)
