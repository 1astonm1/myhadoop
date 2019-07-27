import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;


public class logProcessReducer extends Reducer<Text, FloatWritable, Text, FloatWritable> {
    @Override
    protected void reduce(Text key, Iterable<FloatWritable> values, Context context) throws IOException, InterruptedException {
        FloatWritable valueout = new FloatWritable();

        float totalscore = 0;
        float count = 0;
        float score = 0;
        for(FloatWritable fw:values){
            if(Float.isNaN(fw.get())){
                continue;
            }
            else{
                totalscore = totalscore + fw.get();
                count ++;
            }
        }
        if (count == 0) count = 1;
        score = (float)Math.round((totalscore/count)*100)/100;
//        score = totalscore/count;
        System.out.println("key: " + key);
        System.out.println("value: " + score);
        valueout.set(score);
        context.write(key, valueout);
    }
}
