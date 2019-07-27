import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class logProcessMapper extends Mapper <LongWritable, Text, Text, FloatWritable>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] arr = value.toString().split("\t");        //arr【0】 = key   arr[1] = "评论信息"
        Text keyout = new Text();
        FloatWritable valueout = new FloatWritable();
        keyout.set(arr[0]);
//        arr[1] = arr[1].substring(1, arr[1].length() - 1);      //处理掉首尾“{” ，“}”
//        arr[1] = arr[1].replace("\"", "");
        arr[1] = arr[1].replace("\"", "").replace("[", "").replace("]", "");
        arr[1] = arr[1].replace("{", "").replace("}", "");
        arr[1] = arr[1].replace("desc:,", "").replace("desc:", "").replace("scoreList:", "");
//        System.out.println("arr[1]: " + arr[1]);
//        System.out.println("key: "+ arr[0]);
        String[] lines = arr[1].split(",");
        float totalscore = 0;
        float count = 0;
        for(int i=0; i < lines.length; i ++){
            String[] stringbuf = lines[i].split(":");
            String title = stringbuf[0];
            if (title.equals("score")) {
//                System.out.println(stringbuf[1]);
//                if (stringbuf[1].equals("NaN")){
//                    stringbuf[1] = "0";
//                }
                float score = Float.parseFloat(stringbuf[1]);
                totalscore += score;
                count++;
            }
//            System.out.println("lines["+ i + "] :" + lines[i]);
        }
        float outputscore = totalscore/count;
//        System.out.println("score:" + outputscore);
        valueout.set(outputscore);
        context.write(keyout, valueout);
//        System.out.println("\r\n");
    }
}
