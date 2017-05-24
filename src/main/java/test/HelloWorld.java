package test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017-05-20.
 */
public class HelloWorld {
    public static void change(String str){
        str = "wrggerg";
    }

    public static void main(String[] args) {
        System.out.println("hello world!");
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date();
        Date startDate = new Date(curDate.getTime() - 10 * 1000);
        String strCurDate = dataFormat.format(curDate);
        String strStartDate = dataFormat.format(startDate);
        System.out.println(strCurDate);
        System.out.println(strStartDate);

    }
}
