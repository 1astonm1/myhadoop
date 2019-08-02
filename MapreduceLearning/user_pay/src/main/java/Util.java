import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;

//输出节点信息工具类
public class Util {
    //获得信息
    public static String getInfo(Object o, String msg){
        return getHostname()+ ":" + msg;
    }
    //得到主机名
    public static String getHostname(){
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
    //得到进程ID
    public static int getPID(){
        try {
            String Info = ManagementFactory.getRuntimeMXBean().getName();   //输出是PID@hostname
            return Integer.parseInt(Info.substring(0, Info.indexOf("@")));          //取PID即可
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    //获取线程id
    public static String getTID(){
        try {
            return Thread.currentThread().getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //获取对象信息
    public static String getObjInfo(Object o){
        try {
            String sname = o.getClass().getSimpleName();
            return sname + "@" + o.hashCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
