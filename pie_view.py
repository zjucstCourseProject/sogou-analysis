from collections import Counter

import numpy as np
import matplotlib.pyplot as plt

x = Counter()

DATA_IN ='out/click.ans'

lines = []

with open(DATA_IN,'r',encoding='utf-8') as f:
    lines = f.readlines()

for e in lines:
    e3 = e.split(',')
    if len(e3) != 3:
        continue
    url = e3[0].split('L:')[1].strip()
    clicks = e3[1].split(':')[1].strip()

    x[url] = int(clicks)

def func(pct, allvals):
    absolute = int(pct/100.*np.sum(allvals))
    return "{:.1f}%\n".format(pct, absolute)

plt.pie(
    x=list(x.values()),
    labels=list(x.keys()),
    # autopct=lambda pct: func(pct,list(x.values())),
    autopct='%1.3f%%',
    shadow=False
)
# plt.legend()
plt.show()