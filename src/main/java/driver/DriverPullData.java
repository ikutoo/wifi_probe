package driver;

import conf.ConfigurationManager;
import constants.Constants;
import dataStructs.DataDetailItem;
import dataStructs.DataItem;
import dataStructs.Equipment;
import task.TaskGetDataDetail;
import task.TaskGetEquipments;
import task.TaskGetRecentData;
import util.JDBCHelper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017-05-23.
 */
public class DriverPullData {
    public static void main(String[] args) {
        Timer timer = new Timer();
        TimerPullData timerPullData = new TimerPullData();
        JDBCHelper.getInstanse();
        
        timer.schedule(timerPullData, 0, 1000 * Integer.parseInt(ConfigurationManager.getProperty(Constants.PULL_INTERVAL)));
    }

    public static class TimerPullData extends TimerTask {
        @Override
        public void run() {
            long startMili = System.currentTimeMillis();
            //task0////////////////////////////////////////////////////////////////////////
            ArrayList<String> tableNames = new ArrayList<String>();
            long startMili0 = System.currentTimeMillis();
            TaskGetEquipments task0 = new TaskGetEquipments();
            task0.run();
            long endMili0 = System.currentTimeMillis();
            System.out.println("task0总耗时为：" + (endMili0 - startMili0) + "毫秒");

            ArrayList<Equipment> equipments = task0.getEquipments();
            for (Equipment equipment : equipments) {
                System.out.println(equipment.toString());
                tableNames.add(equipment.getEquipment());
            }
            ////////////////////////////////////////////////////////////////////////

            //task1////////////////////////////////////////////////////////////////////////
            long startMili1 = System.currentTimeMillis();
            TaskGetRecentData task1 = new TaskGetRecentData(tableNames);
            task1.run();
            long endMili1 = System.currentTimeMillis();
            System.out.println("task1总耗时为：" + (endMili1 - startMili1) + "毫秒");

            ArrayList<Integer> startRowIDs = task1.getStartRowIDs();
            ArrayList<Integer> endRowIDs = task1.getEndRowIDs();
            ArrayList<DataItem> dataItems = task1.getDataItems();
            for (DataItem dataItem : dataItems) {
                System.out.println(dataItem.toString());
            }
            ////////////////////////////////////////////////////////////////////////

            //task2////////////////////////////////////////////////////////////////////////
            long startMili2 = System.currentTimeMillis();
            TaskGetDataDetail task2 = new TaskGetDataDetail(startRowIDs, endRowIDs, tableNames);
            task2.run();
            long endMili2 = System.currentTimeMillis();
            System.out.println("task2总耗时为：" + (endMili2 - startMili2) + "毫秒");

            ArrayList<DataDetailItem> dataDetailItems = task2.getDataItems();
            for (DataDetailItem dataItem : dataDetailItems) {
                System.out.println(dataItem.toString());
            }
            ////////////////////////////////////////////////////////////////////////
            long endMili = System.currentTimeMillis();
            System.out.println("所有任务总耗时为：" + (endMili - startMili) + "毫秒");
        }
    }
}
