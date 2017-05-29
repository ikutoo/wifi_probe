package dataStructs;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-05-27.
 */
public class Visit {
    private Integer visitID;
    private String deviceID;
    private String customerID;
    private Timestamp inTime;
    private Timestamp leaveTime;
    private Integer stayTime;
    private Integer visitCircle;

    public Visit() {
    }

    public Visit(Integer visitID, String deviceID, String customerID, Timestamp inTime, Timestamp leaveTime, Integer stayTime, Integer visitCircle) {
        this.visitID = visitID;
        this.deviceID = deviceID;
        this.customerID = customerID;
        this.inTime = inTime;
        this.leaveTime = leaveTime;
        this.stayTime = stayTime;
        this.visitCircle = visitCircle;
    }

    public Integer getVisitID() {
        return visitID;
    }

    public void setVisitID(Integer visitID) {
        this.visitID = visitID;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public Timestamp getInTime() {
        return inTime;
    }

    public void setInTime(Timestamp inTime) {
        this.inTime = inTime;
    }

    public Timestamp getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Timestamp leaveTime) {
        this.leaveTime = leaveTime;
    }

    public Integer getStayTime() {
        return stayTime;
    }

    public void setStayTime(Integer stayTime) {
        this.stayTime = stayTime;
    }

    public Integer getVisitCircle() {
        return visitCircle;
    }

    public void setVisitCircle(Integer visitCircle) {
        this.visitCircle = visitCircle;
    }

    @Override
    public String toString() {
        return "Visit{" +
                "visitID=" + visitID +
                ", deviceID='" + deviceID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", inTime=" + inTime +
                ", leaveTime=" + leaveTime +
                ", stayTime=" + stayTime +
                ", visitCircle=" + visitCircle +
                '}';
    }
}
