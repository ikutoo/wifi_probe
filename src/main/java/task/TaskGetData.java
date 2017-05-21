package task;

import conf.ConfigurationManager;
import dataStructs.DataItem;
import constants.Constants;
import util.JDBCHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2017-05-21.
 */
public class TaskGetData {
    private ArrayList<DataItem> dataItems;

    public TaskGetData() {
        dataItems = new ArrayList<DataItem>();
    }

    public ArrayList<DataItem> getDataItems() {
        return dataItems;
    }

    //查找出记录并将对象给出去方便链式调用
    public TaskGetData run() {
        JDBCHelper helper = JDBCHelper.getInstanse();
        Callback callback = new Callback();
        helper.executeQuery("select * from " + ConfigurationManager.getProperty(Constants.TABLE_DATA), null, callback);
        return this;
    }

    class Callback implements JDBCHelper.QueryCallback {

        public void process(ResultSet rs) throws Exception {
            while (rs.next()) {
                int rowID = rs.getInt(1);
                int id = rs.getInt(2);
                String mmac = rs.getString(3);
                int rate = rs.getInt(4);
                String wssid = rs.getString(5);
                String wmac = rs.getString(6);
                Date date = rs.getDate(7);
                float lat = rs.getFloat(8);
                float lon = rs.getFloat(9);
                String addr = rs.getString(10);

                DataItem dItem = new DataItem(rowID, id, mmac, rate, wssid, wmac, date, lat, lon, addr);
                dataItems.add(dItem);
            }
        }
    }
}
