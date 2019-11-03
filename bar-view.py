from collections import Counter

import matplotlib.pyplot as plt 

DATA_IN = r'out/countTop.out'
plt.rcParams['font.sans-serif']=['SimHei'] #用来正常显示中文标签
plt.rcParams['axes.unicode_minus']=False #用来正常显示负号
x = Counter()
lines =[]

with open(DATA_IN,'r',encoding='utf-8') as f:
    lines = f.readlines()

for e in lines:
    line = e.strip().split(' ')
    k = ''.join(line[:-1])
    
    v = line[-1]
    x[k] = int(v)

plt.bar(range(len(x)), list(x.values()), align='center')
plt.xticks(range(len(x)), list(x.keys()))

plt.show()
