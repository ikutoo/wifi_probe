package task;

import conf.ConfigurationManager;
import constants.Constants;
import dataStructs.Equipment;
import util.JDBCHelper;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017-05-24.
 */
public class TaskGetEquipments {
    private ArrayList<Equipment> equipments;

    public TaskGetEquipments() {
        equipments = new ArrayList<Equipment>();
    }

    public ArrayList<Equipment> getEquipments() {
        return equipments;
    }

    //查找出记录并将对象给出去方便链式调用
    public TaskGetEquipments run() {
        JDBCHelper helper = JDBCHelper.getInstanse();
        TaskGetEquipments.Callback callback = new TaskGetEquipments.Callback();
        helper.executeQuery("select * from " + ConfigurationManager.getProperty(Constants.TABLE_EQUIPMENT), null, callback);
        return this;
    }

    class Callback implements JDBCHelper.QueryCallback {
        public void process(ResultSet rs) throws Exception {
            while (rs.next()) {
                int id = rs.getInt(1);
                String strEquipment = rs.getString(2);

                Equipment equipment = new Equipment(id, strEquipment);
                equipments.add(equipment);
            }
        }
    }
}
