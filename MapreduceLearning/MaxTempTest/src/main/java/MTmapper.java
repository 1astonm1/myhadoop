import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MTmapper extends Mapper <LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text keyout = new Text();
        IntWritable valueout = new IntWritable();

        String line = value.toString();
        keyout.set(line.substring(15, 21));
        int temp = Integer.parseInt(line.substring(87, 92));
        if(temp == 9999){
            temp = 0;
        }
        valueout.set(temp);
        System.out.println("mapper    key : " + keyout + " value: " + valueout);
        context.write(keyout, valueout);
    }
}
