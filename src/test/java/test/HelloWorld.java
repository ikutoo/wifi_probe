package test;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Administrator on 2017-05-20.
 */
public class HelloWorld {
    public static void change(String str) {
        str = "wrggerg";
    }

    public static void main(String[] args) {

        Random random = new Random();
        for (int i = 0; i < 100; ++i) {
            System.out.println(random.nextInt(4));
        }
    }
}
