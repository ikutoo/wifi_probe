package task;

import dataStructs.DataDetailItem;
import dataStructs.DataItem;
import dataStructs.Equipment;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017-05-24.
 */
public class TaskPullData {
    private HashMap<String, ArrayList<DataItem>> mDataItems;
    private HashMap<String, ArrayList<Integer>> mRowIDs;
    private HashMap<String, ArrayList<DataDetailItem>> mDataDetailItems;

    public TaskPullData() {
        mDataItems = new HashMap<String, ArrayList<DataItem>>();
        mDataDetailItems = new HashMap<String, ArrayList<DataDetailItem>>();
        mRowIDs = new HashMap<String, ArrayList<Integer>>();
    }

    public ArrayList<DataItem> getDataItems(String deviceID) {
        return mDataItems.get(deviceID);
    }

    public ArrayList<DataDetailItem> getDataDetailItems(String deviceID) {
        return mDataDetailItems.get(deviceID);
    }

    public ArrayList<Integer> getRowIDs(String deviceID) {
        return mRowIDs.get(deviceID);
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
        mDataItems = task1.getDataMap();
        for (String tableName : tableNames) {
            ArrayList<Integer> rows = new ArrayList<Integer>();
            ArrayList<DataItem> items = mDataItems.get(tableName);
            for (DataItem item : items) {
                rows.add(item.getRowID());
            }
            mRowIDs.put(tableName, rows);
        }
        ////////////////////////////////////////////////////////////////////////

        //task2////////////////////////////////////////////////////////////////////////

        TaskGetDataDetail task2 = new TaskGetDataDetail(startRowIDs, endRowIDs, tableNames);
        task2.run();

        mDataDetailItems = task2.getDataDetailMap();

        ////////////////////////////////////////////////////////////////////////

        long endMili = System.currentTimeMillis();
        System.out.println("TaskPullData总耗时为：" + (endMili - startMili) + " 毫秒");
        System.out.println("//////////leave TaskPullData///////////");
    }
}
