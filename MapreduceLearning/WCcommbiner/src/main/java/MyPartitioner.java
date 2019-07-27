import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class MyPartitioner extends Partitioner<Text, IntWritable> {
    @Override
    public int getPartition(Text text, IntWritable intWritable, int i) {
        if (text.toString().length() <= 3){
            return 0;
        }
        else if((text.toString().length() < 8)&&(text.toString().length() > 3)){
            return 1;
        }
        else return 2;
    }
}
