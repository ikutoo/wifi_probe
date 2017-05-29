package driver;

import conf.ConfigurationManager;
import constants.Constants;
import timer.TimerDBMonitor;
import utils.JDBCHelper;

import java.util.Timer;

/**
 * Created by Administrator on 2017-05-29.
 */
public class DriverDatabaseMonitor {
    public static void main(String[] args) {
        //为单例对象创建实例
        JDBCHelper helper = JDBCHelper.getInstanse();

        //执行定时任务
        Timer timer = new Timer();
        TimerDBMonitor timerDBMonitor = new TimerDBMonitor();
        timer.schedule(timerDBMonitor, 0, 1000 * ConfigurationManager.getInteger(Constants.PROCCESS_INTERVAL));
    }
}
