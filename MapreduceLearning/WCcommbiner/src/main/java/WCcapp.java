import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WCcapp{
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
//        conf.set("fs.defaultFS", "file:///");
        Job job = new Job(conf);

        job.setJarByClass(WCcapp.class);
        job.setJobName("Wcc");
        job.setInputFormatClass(TextInputFormat.class);
//        job.setPartitionerClass(MyPartitioner.class);

        job.setCombinerClass(WCcreducer.class);

//        FileInputFormat.addInputPath(job, new Path("file:///D://DataSet//WordCountCombiner"));
//        FileOutputFormat.setOutputPath(job, new Path("file:///D://DataSet//WordCountCombiner//output"));
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(WCcmapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(WCcreducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        job.setNumReduceTasks(3);

        job.waitForCompletion(true);
    }

}
