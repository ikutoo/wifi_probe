package task;

import dataStructs.DataDetailItem;
import dataStructs.DataItem;
import dataStructs.Equipment;
import global.DataPool;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-24.
 */
public class TaskPullData {
    private ArrayList<String> mDataItems;
    private ArrayList<String> mDataDetailItems;

    public TaskPullData() {
        mDataItems = new ArrayList<String>();
        mDataDetailItems = new ArrayList<String>();
    }

    public ArrayList<String> getDataItems() {
        return mDataItems;
    }

    public ArrayList<String> getDataDetailItems() {
        return mDataDetailItems;
    }

    public void run() {
        long startMili = System.currentTimeMillis();
        System.out.println("//////////enter TaskPullData//////////");

        //task0////////////////////////////////////////////////////////////////////////
        ArrayList<String> tableNames = new ArrayList<String>();

        TaskGetEquipments task0 = new TaskGetEquipments();
        task0.run();

        ArrayList<Equipment> equipments = task0.getEquipments();
        for (Equipment equipment : equipments) {
            //System.out.println(equipment.toString());
            tableNames.add(equipment.getEquipment());
        }
        ////////////////////////////////////////////////////////////////////////

        //task1////////////////////////////////////////////////////////////////////////

        TaskGetRecentData task1 = new TaskGetRecentData(tableNames);
        task1.run();

        ArrayList<Integer> startRowIDs = task1.getStartRowIDs();
        ArrayList<Integer> endRowIDs = task1.getEndRowIDs();
        ArrayList<DataItem> dataItems = task1.getDataItems();
        for (DataItem dataItem : dataItems) {
            //System.out.println(dataItem.toString2());
            mDataItems.add(dataItem.toString2());
        }
        ////////////////////////////////////////////////////////////////////////

        //task2////////////////////////////////////////////////////////////////////////

        TaskGetDataDetail task2 = new TaskGetDataDetail(startRowIDs, endRowIDs, tableNames);
        task2.run();

        ArrayList<DataDetailItem> dataDetailItems = task2.getDataItems();
        for (DataDetailItem dataItem : dataDetailItems) {
            //System.out.println(dataItem.toString2());
            mDataDetailItems.add(dataItem.toString2());
        }
        ////////////////////////////////////////////////////////////////////////

        long endMili = System.currentTimeMillis();
        System.out.println("TaskPullData总耗时为：" + (endMili - startMili) + " 毫秒");
        System.out.println("//////////leave TaskPullData///////////");
    }
}
