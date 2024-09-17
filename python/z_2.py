import math
x=1.825*(10**2)
y=18.225
z=-3.298*(10**-2)
s=(math.fabs(math.pow(x, y/x)- ((y/x)**(1./3.))))+ (y-x)*(((math.cos(y))-z/(y-x))/(1+math.pow(y-x,2)))
print (s)
