package task;

import conf.ConfigurationManager;
import constants.Constants;
import dataStructs.Customer;
import utils.JDBCHelper;

/**
 * Created by Administrator on 2017-05-29.
 */
public class TaskUpdateCustomer {
    private Customer customer;

    public TaskUpdateCustomer(Customer customer) {
        this.customer = customer;
    }

    public TaskUpdateCustomer run() {
        JDBCHelper helper = JDBCHelper.getInstanse();

        String strSql = String.format("replace into %s values('%s','%s', %d, %d, %d, %d, %d)",
                ConfigurationManager.getProperty(Constants.TABLE_CUSTOMERS),
                customer.getDeviceID(),
                customer.getCustomerID(),
                customer.getVisitTimes(),
                customer.getAvgStayTime(),
                customer.getbOldCustomer(),
                customer.getAvgVisitCircle(),
                customer.getLivenessLevel());
        helper.execute(strSql, null);
        return this;
    }
}
