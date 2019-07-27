public class Test {

    public static  void logsprocess(){
        String arr = "{\"reviewPics\":null,\"extInfoList\":null,\"expenseList\":null,\"reviewIndexes\":[2],\"scoreList\":[{\"score\":4,\"title\":\"口味\",\"desc\":\"\"},{\"score\":3,\"title\":\"服务\",\"desc\":\"\"},{\"score\":3,\"title\":\"环境\",\"desc\":\"\"}]}";
//        arr = arr.substring(1, arr.length() -1);
        arr = arr.replace("\"", "").replace("[", "").replace("]", "");
        arr = arr.replace("{", "").replace("}", "");
        arr = arr.replace("desc:,", "").replace("desc:", "").replace("scoreList:", "");

//        arr = arr.replace(":", " : ");
        System.out.println(arr);
        String[] list = arr.split(",");
        int totlesecore = 0;
        float count = 0;
        float output = 0;
        for(int i = 0; i < list.length; i++){
            String[] split = list[i].split(":");
            String title = split[0];
            String con = split[1];

            if (title.equals( "score")){
                totlesecore = totlesecore +Integer.parseInt(con);
                count ++;
            }
//            System.out.println("list["+i+"]    " + list[i]);
        }
        output = totlesecore/count;
        System.out.println(output);
    }


    public static void main(String[] args) {
        logsprocess();
    }

}
