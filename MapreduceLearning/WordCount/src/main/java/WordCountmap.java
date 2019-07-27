import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WordCountmap extends Mapper <LongWritable, Text, Text, IntWritable>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text keyOut = new Text();
        IntWritable valueOut = new IntWritable();
        System.out.println("value:" + value);
        String[] arr = value.toString().split(" ");
        for (int i = 0; i < arr.length; i++){
            keyOut.set(arr[i]);
            valueOut.set(1);
            context.write(keyOut, valueOut);
        }
    }
}
