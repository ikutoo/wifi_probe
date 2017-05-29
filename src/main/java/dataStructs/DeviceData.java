package dataStructs;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-05-27.
 */
public class DeviceData {
    private String deviceID;
    private Timestamp dateTime;
    private int customerFlow;
    private int inStoreFlow;
    private float inStoreRate;
    private float bounceRate;
    private float deepVisitRate;

    public DeviceData() {
    }

    public DeviceData(String deviceID, Timestamp dateTime, int customerFlow, int inStoreFlow, float inStoreRate, float bounceRate, float deepVisitRate) {
        this.deviceID = deviceID;
        this.dateTime = dateTime;
        this.customerFlow = customerFlow;
        this.inStoreFlow = inStoreFlow;
        this.inStoreRate = inStoreRate;
        this.bounceRate = bounceRate;
        this.deepVisitRate = deepVisitRate;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public int getCustomerFlow() {
        return customerFlow;
    }

    public void setCustomerFlow(int customerFlow) {
        this.customerFlow = customerFlow;
    }

    public int getInStoreFlow() {
        return inStoreFlow;
    }

    public void setInStoreFlow(int inStoreFlow) {
        this.inStoreFlow = inStoreFlow;
    }

    public float getInStoreRate() {
        return inStoreRate;
    }

    public void setInStoreRate(float inStoreRate) {
        this.inStoreRate = inStoreRate;
    }

    public float getBounceRate() {
        return bounceRate;
    }

    public void setBounceRate(float bounceRate) {
        this.bounceRate = bounceRate;
    }

    public float getDeepVisitRate() {
        return deepVisitRate;
    }

    public void setDeepVisitRate(float deepVisitRate) {
        this.deepVisitRate = deepVisitRate;
    }

    @Override
    public String toString() {
        return "DeviceData{" +
                "deviceID=" + deviceID +
                ", dateTime=" + dateTime +
                ", customerFlow=" + customerFlow +
                ", inStoreFlow=" + inStoreFlow +
                ", inStoreRate=" + inStoreRate +
                ", bounceRate=" + bounceRate +
                ", deepVisitRate=" + deepVisitRate +
                '}';
    }
}
