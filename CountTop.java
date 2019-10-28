/*
* author : zouzhitao
*/

import javafx.util.Pair;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;


public class CountTop {
    public static final int TOPK = 30;
    public static class TopMapper  extends Mapper<LongWritable, Text, Text, LongWritable>{
        Text text =new Text();

        @Override
        protected void map(LongWritable key, Text value,Context context)
                throws IOException, InterruptedException {
            String[] line= value.toString().split("\t");
            String keys = line[2];
            text.set(keys);
            context.write(text,new LongWritable(1));
        }
    }


    public static class TopReducer extends Reducer< Text,LongWritable, Text, LongWritable>{
        Text text = new Text();
        // TreeMap<Integer,String > map = new TreeMap<Integer,String>();
        PriorityQueue<Pair<String,Long> > PQ = new PriorityQueue<>(new Comparator<Pair<String, Long>>() {
            @Override
            public int compare(Pair<String, Long> o1, Pair<String, Long> o2) {
                return (int)(o1.getValue() - o2.getValue());
            }
        });
        @Override
        protected void reduce(Text key, Iterable<LongWritable> value, Context context)
                throws IOException, InterruptedException {
            Long sum=(long)0;//key出现次数
            for (LongWritable ltext : value) {
                sum+=ltext.get();
            }
            PQ.add(new Pair<>(key.toString(),sum));
            //only need topk
            if(PQ.size()>CountTop.TOPK){
                PQ.poll();

            }
            assert PQ.size() <= CountTop.TOPK;
        }

        @Override
        protected void cleanup(Context context)
                throws IOException, InterruptedException {
//            for(Integer count:map.keySet()){
//                context.write(new Text(map.get(count)), new LongWritable(count));
//            }
            ArrayList<Pair<String,Long>> al = new ArrayList<>();
            while (!PQ.isEmpty()){
                Pair<String,Long> p = PQ.poll();
                al.add(p);
            }
            for(int i=al.size() -1 ;i>=0 ; --i){
                System.out.println(al.get(i).getKey()+" "+al.get(i).getValue());
                context.write(new Text(al.get(i).getKey()), new LongWritable(al.get(i).getValue()));
            }
        }
    }



    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://localhost:9000");

        Job job = Job.getInstance(conf, "count");
        job.setJarByClass(CountTop.class);
        job.setJobName("Five");

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        job.setMapperClass(TopMapper.class);
        job.setReducerClass(TopReducer.class);



        FileInputFormat.addInputPath(job, new Path("/sogou/data/sogou.500w.utf8"));
        FileOutputFormat.setOutputPath(job, new Path("out/count.out"));

        job.waitForCompletion(true);
    }




}