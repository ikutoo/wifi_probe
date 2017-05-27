package global;

import dataStructs.DataDetailItem;
import dataStructs.DataItem;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 2017-05-24.
 */
public class DataPool {
    private static DataPool mInstance = new DataPool();

    private HashMap<String, ArrayList<DataItem>> dataItems;

    private HashMap<String, ArrayList<DataDetailItem>> dataDetailItems;

    private HashMap<String, ArrayList<Integer>> rowIDs;

    public static DataPool getInstance() {
        return mInstance;
    }

    private DataPool() {
        dataItems = new HashMap<String, ArrayList<DataItem>>();
        dataDetailItems = new HashMap<String, ArrayList<DataDetailItem>>();
        rowIDs = new HashMap<String, ArrayList<Integer>>();
    }

    public ArrayList<DataItem> getDataItems(String deviceID) {
        if (dataItems.containsKey(deviceID))
            return dataItems.get(deviceID);
        else
            return new ArrayList<DataItem>();
    }

    public ArrayList<DataDetailItem> getDataDetailItems(String deviceID) {
        if (dataDetailItems.containsKey(deviceID))
            return dataDetailItems.get(deviceID);
        else
            return new ArrayList<DataDetailItem>();
    }

    public ArrayList<Integer> getRowIDs(String deviceID) {
        if (rowIDs.containsKey(deviceID))
            return rowIDs.get(deviceID);
        else
            return new ArrayList<Integer>();
    }

    public void addDataItem(String deviceID, ArrayList<DataItem> items) {
        if (dataItems.containsKey(deviceID))
            dataItems.remove(deviceID);
        dataItems.put(deviceID, items);
    }

    public void addDataDetailItem(String deviceID, ArrayList<DataDetailItem> items) {
        if (dataDetailItems.containsKey(deviceID))
            dataDetailItems.remove(deviceID);
        dataDetailItems.put(deviceID, items);
    }

    public void addRowIDs(String deviceID, ArrayList<Integer> ids) {
        if (rowIDs.containsKey(deviceID))
            rowIDs.remove(deviceID);
        rowIDs.put(deviceID, ids);
    }

    public void clearPool() {
        dataItems.clear();
        dataDetailItems.clear();
        rowIDs.clear();
    }
}
