import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class logProcessAPP {
    public static void main(String[] args) throws Exception{
        Configuration conf = new Configuration();
        Job job = new Job(conf);

        job.setJarByClass(logProcessAPP.class);
        job.setJobName("LogProcess");
        job.setInputFormatClass(TextInputFormat.class);

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(logProcessMapper.class);
        job.setMapOutputValueClass(FloatWritable.class);
        job.setMapOutputKeyClass(Text.class);

        job.setReducerClass(logProcessReducer.class);
        job.setNumReduceTasks(1);
        job.setOutputValueClass(FloatWritable.class);
        job.setOutputKeyClass(Text.class);

        System.exit(job.waitForCompletion(true)?0:1);

    }
}
