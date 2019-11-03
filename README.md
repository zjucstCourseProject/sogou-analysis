# 注意事项

1. 需要把数据解压后放到./data/下
2. 然后才能使用./preprocess.sh处理数据
3. 然后再使用./hadoop_add_data.sh添加数据（需要先启动hadoop、格式化结点）
4. 然后按照给的2019-11-01-搜狗数据分析.md进行数据分析

# 目录说明

out/ 结果图片或txt

*.java hadoop MR java 文件，执行前需打包成jar

*_view.py 绘图文件，基于matplotlib.pyplot，分别绘制bar图与pie图

preprocess.sh 数据预处理 sh

hadoop_add_data.sh hadoop田间数据文件，需要先启动初始化hadoop

main.py 简单spark程序，功能与 CountRank.java 一致

# 添加您的同样的coursework到此项目

 如果你有同样的作业需要添加，可clone 此项目，chechout一个新分支，以你自己的名字命名，push到你自己的分支，然后修改此README，添加你的分支地址

即:

```sh
git clone git@github.com:zjucstCourseProject/sogou-analysis.git

git checkout -b [你的名字/分支名字*]

git add [你的内容] (注意不要把master 分支的类容又从新add一遍)

git push -u [您的名字/分支名字*]
```

完成后修改贡献者名单

```
[github account] [分支链接](分支url)
```

# 添加新的资料

直接提交PR

# 贡献者

- [tt](https://github.com/zouzhitao)  [master](https://github.com/zjucstCourseProject/sogou-analysis)