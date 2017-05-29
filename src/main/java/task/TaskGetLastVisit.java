package task;

import conf.ConfigurationManager;
import constants.Constants;
import dataStructs.Equipment;
import dataStructs.Visit;
import utils.JDBCHelper;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-05-27.
 */
public class TaskGetLastVisit {
    private Visit visit = null;
    private String deviceID;
    private String customerID;

    public TaskGetLastVisit(String deviceID, String customerID) {
        this.deviceID = deviceID;
        this.customerID = customerID;
    }

    public Visit getVisit() {
        return visit;
    }

    public TaskGetLastVisit run() {
        JDBCHelper helper = JDBCHelper.getInstanse();
        TaskGetLastVisit.Callback callback = new TaskGetLastVisit.Callback();
        String strSql = String.format("select * from %s where deviceID= '%s' and customerID= '%s' " +
                        "and visitID = (select max(visitID) from %s where deviceID= '%s' and customerID= '%s')",
                ConfigurationManager.getProperty(Constants.TABLE_VISITS), deviceID, customerID,
                ConfigurationManager.getProperty(Constants.TABLE_VISITS), deviceID, customerID);
        helper.executeQuery(strSql, null, callback);
        return this;
    }

    class Callback implements JDBCHelper.QueryCallback {
        public void process(ResultSet rs) throws Exception {
            if (rs.next()) {
                Integer visitID = rs.getInt(1);
                String deviceID = rs.getString(2);
                String customerID = rs.getString(3);
                Timestamp inTime = rs.getTimestamp(4);
                Timestamp leaveTime = rs.getTimestamp(5);
                Integer stayTime = rs.getInt(6);
                Integer visitCircle = rs.getInt(7);
                visit = new Visit(
                        visitID,
                        deviceID,
                        customerID,
                        inTime,
                        leaveTime,
                        stayTime,
                        visitCircle
                );
            }
        }
    }
}
