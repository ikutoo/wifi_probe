package task;

import conf.ConfigurationManager;
import constants.Constants;
import dataStructs.DeviceData;
import utils.JDBCHelper;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-05-27.
 */
public class TaskGetDeviceData {
    private DeviceData deviceData = null;
    private String deviceID;
    private Timestamp dateTime;

    public TaskGetDeviceData(String deviceID, Timestamp dateTime) {
        this.deviceID = deviceID;
        this.dateTime = dateTime;
    }

    public DeviceData getDeviceData() {
        return deviceData;
    }

    public TaskGetDeviceData run() {
        JDBCHelper helper = JDBCHelper.getInstanse();
        TaskGetDeviceData.Callback callback = new TaskGetDeviceData.Callback();
        String strSql = String.format("select * from %s where deviceID= '%s' and time= '%s'",
                ConfigurationManager.getProperty(Constants.TABLE_DEVICEDATA), deviceID, dateTime.toString());
        helper.executeQuery(strSql, null, callback);
        return this;
    }

    class Callback implements JDBCHelper.QueryCallback {
        public void process(ResultSet rs) throws Exception {
            if (rs.next()) {
                String deviceID = rs.getString(1);
                Timestamp dateTime = rs.getTimestamp(2);
                int customerFlow = rs.getInt(3);
                int inStoreFlow = rs.getInt(4);
                float inStoreRate = rs.getFloat(5);
                float bounceRate = rs.getFloat(6);
                float deepVisitRate = rs.getFloat(7);

                deviceData = new DeviceData(
                        deviceID,
                        dateTime,
                        customerFlow,
                        inStoreFlow,
                        inStoreRate,
                        bounceRate,
                        deepVisitRate);
            }
        }
    }
}
