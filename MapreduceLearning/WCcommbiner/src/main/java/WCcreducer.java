import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WCcreducer extends Reducer<Text, IntWritable, Text, IntWritable>{
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        for (IntWritable iw:values){
            count = count + iw.get();
        }
//        String tno = Thread.currentThread().getName();

//        System.out.println(tno + "      Key:" + key.toString() + "    value: " + count);
        context.write(key, new IntWritable(count));
        context.getCounter("reducer", Util.getInfo(this, "reducer")).increment(1);
    }
}
