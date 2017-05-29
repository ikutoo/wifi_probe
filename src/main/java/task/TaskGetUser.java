package task;

import conf.ConfigurationManager;
import constants.Constants;
import dataStructs.User;
import utils.JDBCHelper;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-21.
 */
public class TaskGetUser {
    private ArrayList<User> users;

    public TaskGetUser() {
        users = new ArrayList<User>();
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    //查找出记录并将对象给出去方便链式调用
    public TaskGetUser run() {
        JDBCHelper helper = JDBCHelper.getInstanse();
        TaskGetUser.Callback callback = new TaskGetUser.Callback();
        helper.executeQuery("select * from " + ConfigurationManager.getProperty(Constants.TABLE_USER), null, callback);
        return this;
    }

    class Callback implements JDBCHelper.QueryCallback {
        public void process(ResultSet rs) throws Exception {
            while (rs.next()) {
                int userID = rs.getInt(1);
                String userAccount = rs.getString(2);
                String userPassword = rs.getString(3);

                User user = new User(userID, userAccount, userPassword);
                users.add(user);
            }
        }
    }
}