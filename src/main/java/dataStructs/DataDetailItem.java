package dataStructs;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-05-20.
 */
public class DataDetailItem {
    private int rowID;
    private int masterID;
    private Timestamp dateTime;
    private String mac;
    private int rssi;
    private float range;
    private String ts;
    private String tc;
    private String tmc;
    private String ds;
    private String essid1;
    private String essid2;
    private String essid3;
    private String essid4;
    private String essid5;
    private String essid6;

    public DataDetailItem() {

    }

    public DataDetailItem(int rowID, int masterID, Timestamp dateTime, String mac, int rssi, float range, String ts, String tc, String tmc, String ds, String essid1, String essid2, String essid3, String essid4, String essid5, String essid6) {
        this.rowID = rowID;
        this.masterID = masterID;
        this.dateTime = dateTime;
        this.mac = mac;
        this.rssi = rssi;
        this.range = range;
        this.ts = ts;
        this.tc = tc;
        this.tmc = tmc;
        this.ds = ds;
        this.essid1 = essid1;
        this.essid2 = essid2;
        this.essid3 = essid3;
        this.essid4 = essid4;
        this.essid5 = essid5;
        this.essid6 = essid6;
    }

    public int getRowID() {
        return rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    public int getMasterID() {
        return masterID;
    }

    public void setMasterID(int masterID) {
        this.masterID = masterID;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public int getRssi() {
        return rssi;
    }

    public void setRssi(int rssi) {
        this.rssi = rssi;
    }

    public float getRange() {
        return range;
    }

    public void setRange(float range) {
        this.range = range;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public String getTc() {
        return tc;
    }

    public void setTc(String tc) {
        this.tc = tc;
    }

    public String getTmc() {
        return tmc;
    }

    public void setTmc(String tmc) {
        this.tmc = tmc;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getEssid1() {
        return essid1;
    }

    public void setEssid1(String essid1) {
        this.essid1 = essid1;
    }

    public String getEssid2() {
        return essid2;
    }

    public void setEssid2(String essid2) {
        this.essid2 = essid2;
    }

    public String getEssid3() {
        return essid3;
    }

    public void setEssid3(String essid3) {
        this.essid3 = essid3;
    }

    public String getEssid4() {
        return essid4;
    }

    public void setEssid4(String essid4) {
        this.essid4 = essid4;
    }

    public String getEssid5() {
        return essid5;
    }

    public void setEssid5(String essid5) {
        this.essid5 = essid5;
    }

    public String getEssid6() {
        return essid6;
    }

    public void setEssid6(String essid6) {
        this.essid6 = essid6;
    }

    @Override
    public String toString() {
        return "DataDetailItem{" +
                "rowID=" + rowID +
                ", masterID=" + masterID +
                ", dateTime=" + dateTime +
                ", mac='" + mac + '\'' +
                ", rssi=" + rssi +
                ", range=" + range +
                ", ts='" + ts + '\'' +
                ", tc='" + tc + '\'' +
                ", tmc='" + tmc + '\'' +
                ", ds='" + ds + '\'' +
                ", essid1='" + essid1 + '\'' +
                ", essid2='" + essid2 + '\'' +
                ", essid3='" + essid3 + '\'' +
                ", essid4='" + essid4 + '\'' +
                ", essid5='" + essid5 + '\'' +
                ", essid6='" + essid6 + '\'' +
                '}';
    }
}

