start-dfs.sh
start-yarn.sh
hadoop fs -mkdir -p /sogou
hadoop fs -mkdir -p /sogou/data
hadoop fs -put data/sogou.500w.utf8 /sogou/data
hadoop fs -put data/sogou.500w.utf8.flt /sogou_ext/data
