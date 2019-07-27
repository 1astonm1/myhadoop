import org.apache.hadoop.io.IntWritable;
        import org.apache.hadoop.io.LongWritable;
        import org.apache.hadoop.io.Text;
        import org.apache.hadoop.mapreduce.Mapper;

        import java.io.IOException;

public class User_paymapper  extends Mapper <LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        Text keyout = new Text();
        IntWritable valueout = new IntWritable();

        String[] arr = value.toString().split(",");     //按逗号切开后，0 = 用户id， 1 = 卖家ID 2 = 时间信息
        keyout.set(arr[1]);
        valueout.set(1);
//        System.out.println("key:" + keyout.toString());
        context.write(keyout, valueout);
    }
}
