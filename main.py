from pyspark import SparkConf,SparkContext

FILE_IN = 'data/sogou.500w.utf8'

conf = SparkConf()
sc = SparkContext(conf=conf)

lines = sc.textFile(FILE_IN)

ans = lines.map(
    lambda x : (x.split('\t')[-1],1)
).reduceByKey(
    lambda n1,n2: n1+n2
).map(
    lambda x : (x[1],x[0])
).top(10)

SUM = 5000000
print('CTR TOP 10:')

for e in ans:
    print('URL: {} , #click: {} , ratio: {}%'.format(e[1],e[0],e[0]/SUM *100))

