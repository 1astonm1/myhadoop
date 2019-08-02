import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class MTreducer extends Reducer<Text, IntWritable, Text, IntWritable>{
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int maxtemp = 0;

        for(IntWritable iw:values){
            if(maxtemp < iw.get()){
                maxtemp = iw.get();
            }
        }
        System.out.println("Reducer    key : " + key + " value: " + maxtemp);
        context.write(key, new IntWritable(maxtemp));
    }
}
