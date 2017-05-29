package dataStructs;

/**
 * Created by Administrator on 2017-05-27.
 */
public class Customer {
    private String deviceID;
    private String customerID;
    private int visitTimes;
    private int avgStayTime;
    private int bOldCustomer;
    private int avgVisitCircle;
    private int livenessLevel;

    public Customer() {
    }

    public Customer(String deviceID, String customerID, int visitTimes, int avgStayTime, int bOldCustomer, int avgVisitCircle, int livenessLevel) {
        this.deviceID = deviceID;
        this.customerID = customerID;
        this.visitTimes = visitTimes;
        this.avgStayTime = avgStayTime;
        this.bOldCustomer = bOldCustomer;
        this.avgVisitCircle = avgVisitCircle;
        this.livenessLevel = livenessLevel;
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

    public int getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(int visitTimes) {
        this.visitTimes = visitTimes;
    }

    public int getAvgStayTime() {
        return avgStayTime;
    }

    public void setAvgStayTime(int avgStayTime) {
        this.avgStayTime = avgStayTime;
    }

    public int getbOldCustomer() {
        return bOldCustomer;
    }

    public void setbOldCustomer(int bOldCustomer) {
        this.bOldCustomer = bOldCustomer;
    }

    public int getAvgVisitCircle() {
        return avgVisitCircle;
    }

    public void setAvgVisitCircle(int avgVisitCircle) {
        this.avgVisitCircle = avgVisitCircle;
    }

    public int getLivenessLevel() {
        return livenessLevel;
    }

    public void setLivenessLevel(int livenessLevel) {
        this.livenessLevel = livenessLevel;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "deviceID='" + deviceID + '\'' +
                ", customerID='" + customerID + '\'' +
                ", visitTimes=" + visitTimes +
                ", avgStayTime=" + avgStayTime +
                ", bOldCustomer=" + bOldCustomer +
                ", avgVisitCircle=" + avgVisitCircle +
                ", livenessLevel=" + livenessLevel +
                '}';
    }
}
