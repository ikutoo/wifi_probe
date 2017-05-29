package task;

import conf.ConfigurationManager;
import constants.Constants;
import dataStructs.Visit;
import utils.JDBCHelper;

/**
 * Created by Administrator on 2017-05-27.
 */
public class TaskUpdateVisit {
    private Visit visit;

    public TaskUpdateVisit(Visit visit) {
        this.visit = visit;
    }

    public TaskUpdateVisit run() {
        JDBCHelper helper = JDBCHelper.getInstanse();

        String strLeaveTime;
        if (visit.getLeaveTime() == null) {
            strLeaveTime = "NULL";
        } else {
            strLeaveTime = "'" + visit.getLeaveTime().toString() + "'";
        }
        String strSql = String.format("replace into %s values(%d,'%s','%s','%s',%s, %d, %d)",
                ConfigurationManager.getProperty(Constants.TABLE_VISITS),
                visit.getVisitID(),
                visit.getDeviceID(),
                visit.getCustomerID(),
                visit.getInTime().toString(),
                strLeaveTime,
                visit.getStayTime(),
                visit.getVisitCircle());
        helper.execute(strSql, null);
        return this;
    }
}
