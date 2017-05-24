package global;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-24.
 */
public class DataPool {
    private static DataPool mInstance = new DataPool();

    private ArrayList<String> dataItems;

    private ArrayList<String> dataDetailItems;

    public static DataPool getInstance() {
        return mInstance;
    }

    private DataPool() {
        dataItems = new ArrayList<String>();
        dataDetailItems = new ArrayList<String>();
    }

    public ArrayList<String> getDataItems() {
        return dataItems;
    }

    public ArrayList<String> getDataDetailItems() {
        return dataDetailItems;
    }

    public void addDataItem(String str) {
        dataItems.add(str);
    }

    public void addDataDetailItem(String str) {
        dataDetailItems.add(str);
    }

    public void clearPool() {
        dataItems.clear();
        dataDetailItems.clear();
    }
}
