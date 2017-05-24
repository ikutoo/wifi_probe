package task;

import dataStructs.DataDetailItem;
import util.JDBCHelper;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-24.
 */
public class TaskGetDataDetail {
    private ArrayList<DataDetailItem> dataItems;
    private ArrayList<Integer> startRowIDs;
    private ArrayList<Integer> endRowIDs;
    private ArrayList<String> tableNames;

    public TaskGetDataDetail(ArrayList<Integer> startRowIDs, ArrayList<Integer> endRowIDs, ArrayList<String> tableNames) {
        this.startRowIDs = startRowIDs;
        this.endRowIDs = endRowIDs;
        this.tableNames = tableNames;
        dataItems = new ArrayList<DataDetailItem>();
    }

    public ArrayList<DataDetailItem> getDataItems() {
        return dataItems;
    }


    //查找出记录并将对象给出去方便链式调用
    public TaskGetDataDetail run() {
        JDBCHelper helper = JDBCHelper.getInstanse();
        TaskGetDataDetail.Callback callback = new TaskGetDataDetail.Callback();

        for (int i = 0; i < tableNames.size(); ++i) {
            String sql = String.format("select * from %s where master_id between %d and %d", tableNames.get(i)+"_detail", startRowIDs.get(i), endRowIDs.get(i));
            helper.executeQuery(sql, null, callback);
        }
        return this;
    }

    class Callback implements JDBCHelper.QueryCallback {

        public void process(ResultSet rs) throws Exception {
            while (rs.next()) {
                int rowID = rs.getInt(1);
                int masterID = rs.getInt(2);
                Timestamp dateTime = rs.getTimestamp(3);
                String mac = rs.getString(4);
                int rssi = rs.getInt(5);
                float range = rs.getFloat(6);
                String ts = rs.getString(7);
                String tc = rs.getString(8);
                String tmc = rs.getString(9);
                String ds = rs.getString(10);
                String essid1 = rs.getString(11);
                String essid2 = rs.getString(12);
                String essid3 = rs.getString(13);
                String essid4 = rs.getString(14);
                String essid5 = rs.getString(15);
                String essid6 = rs.getString(16);

                DataDetailItem dataItem = new DataDetailItem(
                        rowID,
                        masterID,
                        dateTime,
                        mac,
                        rssi,
                        range,
                        ts,
                        tc,
                        tmc,
                        ds,
                        essid1,
                        essid2,
                        essid3,
                        essid4,
                        essid5,
                        essid6
                );
                dataItems.add(dataItem);
            }
        }
    }
}
