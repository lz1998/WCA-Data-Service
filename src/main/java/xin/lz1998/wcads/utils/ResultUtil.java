package xin.lz1998.wcads.utils;

public class ResultUtil {
    public static String formatTime(int result,String eventId){
//        https://github.com/CubingChina/cubingchina/blob/cddf9cd22d13cc9f539559d4ac3bfd1e5fde0948/protected/models/wca/Results.php
        String resStr =String.valueOf(result);
        if(result==-1){
            return "DNF";
        }
        if(result==-2){
            return "DNS";
        }
        if(result==3){
            return "";
        }
        if ("333fm".equals(eventId)) {
            if(result>1000){
                return String.format("%.2f",(double)result/100);
            }else{
                return String.valueOf(result);
            }
        }
        return "";

    }
}
