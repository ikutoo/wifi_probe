package test;

import dataStructs.User;
import task.TaskGetUser;
import util.JDBCHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-20.
 */
public class AccessDB {
    public static void main(String[] args) {

//        TaskGetData task = new TaskGetData();
//        task.run();
//        ArrayList<DataItem> dataItems = task.getDataItems();
//
//        for (DataItem dataItem : dataItems) {
//            System.out.println(dataItem.toString());
//        }
        //JDBCHelper helper = JDBCHelper.getInstanse();

        long startMili = System.currentTimeMillis();
        TaskGetUser task = new TaskGetUser();
        task.run();
        long endMili = System.currentTimeMillis();
        System.out.println("总耗时为：" + (endMili - startMili) + "毫秒");
        ArrayList<User> users = task.getUsers();

        for (User user : users) {
            System.out.println(user.toString());
        }


        long startMili2 = System.currentTimeMillis();
        TaskGetUser task2 = new TaskGetUser();
        task2.run();
        long endMili2 = System.currentTimeMillis();
        System.out.println("总耗时为：" + (endMili2 - startMili2) + "毫秒");
        ArrayList<User> users2 = task.getUsers();

        for (User user2 : users2) {
            System.out.println(user2.toString());
        }
    }
}
