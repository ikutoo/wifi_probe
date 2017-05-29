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
public class TaskUpdateDeviceData {
    private DeviceData deviceData;

    public TaskUpdateDeviceData(DeviceData deviceData) {
        this.deviceData = deviceData;
    }

    public TaskUpdateDeviceData run() {
        JDBCHelper helper = JDBCHelper.getInstanse();

        String strSql = String.format("replace into %s values('%s','%s',%d,%d,%f,%f,%f)",
                ConfigurationManager.getProperty(Constants.TABLE_DEVICEDATA),
                deviceData.getDeviceID(),
                deviceData.getDateTime(),
                deviceData.getCustomerFlow(),
                deviceData.getInStoreFlow(),
                deviceData.getInStoreRate(),
                deviceData.getBounceRate(),
                deviceData.getDeepVisitRate());
        helper.execute(strSql, null);
        return this;
    }
}
