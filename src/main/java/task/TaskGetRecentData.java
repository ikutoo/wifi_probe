package task;

import conf.ConfigurationManager;
import constants.Constants;
import dataStructs.DataItem;
import scala.runtime.StringFormat;
import util.JDBCHelper;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2017-05-23.
 */
public class TaskGetRecentData {
    private ArrayList<DataItem> dataItems;
    private ArrayList<Integer> startRowIDs;
    private ArrayList<Integer> endRowIDs;
    private ArrayList<String> tableNames;

    public TaskGetRecentData(ArrayList<String> tableNames) {
        this.tableNames = tableNames;
        dataItems = new ArrayList<DataItem>();
        startRowIDs = new ArrayList<Integer>();
        endRowIDs = new ArrayList<Integer>();
    }

    public ArrayList<DataItem> getDataItems() {
        return dataItems;
    }

    public ArrayList<Integer> getStartRowIDs() {
        return startRowIDs;
    }

    public ArrayList<Integer> getEndRowIDs() {
        return endRowIDs;
    }

    //查找出记录并将对象给出去方便链式调用
    public TaskGetRecentData run() {
        JDBCHelper helper = JDBCHelper.getInstanse();
        Callback callback = new Callback();

        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date();
        Date startDate = new Date(curDate.getTime() - Integer.parseInt(ConfigurationManager.getProperty(Constants.PULL_INTERVAL)) * 1000);
        String strCurDate = dataFormat.format(curDate);
        String strStartDate = dataFormat.format(startDate);

        for (String tableName : tableNames) {
            String sql = String.format("select * from %s where date between '%s' and '%s'", tableName, strStartDate, strCurDate);
            helper.executeQuery(sql, null, callback);
        }

        return this;
    }

    class Callback implements JDBCHelper.QueryCallback {

        public void process(ResultSet rs) throws Exception {
            int startItem = dataItems.size();
            while (rs.next()) {
                int rowID = rs.getInt(1);
                int id = rs.getInt(2);
                String mmac = rs.getString(3);
                int rate = rs.getInt(4);
                String wssid = rs.getString(5);
                String wmac = rs.getString(6);
                java.sql.Timestamp date = rs.getTimestamp(7);
                float lat = rs.getFloat(8);
                float lon = rs.getFloat(9);
                String addr = rs.getString(10);

                DataItem dItem = new DataItem(rowID, id, mmac, rate, wssid, wmac, date, lat, lon, addr);
                dataItems.add(dItem);
            }
            int endItem = dataItems.size() - 1;
            startRowIDs.add(dataItems.get(startItem).getRowID());
            endRowIDs.add(dataItems.get(endItem).getRowID());
        }
    }
}
