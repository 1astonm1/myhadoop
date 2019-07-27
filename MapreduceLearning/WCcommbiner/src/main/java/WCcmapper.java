import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WCcmapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text keyout = new Text();
        IntWritable valueout = new IntWritable();

        String[] arr = value.toString().split(" ");
        for(int i=0; i < arr.length; i++){
//            System.out.println("value:" + arr[i]);
            keyout.set(arr[i]);
            valueout.set(1);
            context.write(keyout, valueout);
        }
    }
}
