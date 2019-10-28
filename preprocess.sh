#!/bin/bash
infile=data/sogou.500w.utf8
# infile=$1
outfile=data/sogou.500w.utf8.ext
# outfile=$2
awk -F '\t' '{print $0"\t"substr($1,0,5)"year\t"substr($1,5,2)"month\t"substr($1,7,2)"day\t"substr($1,8,2)"hour"}' $infile > $outfile

