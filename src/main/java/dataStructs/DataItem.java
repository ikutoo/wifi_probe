package dataStructs;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2017-05-20.
 */
public class DataItem {
    private int rowID;
    private int id;
    private String mmac;
    private int rate;
    private String wssid;
    private String wmac;
    private Timestamp date;
    private float lat;
    private float lon;
    private String addr;


    public DataItem() {

    }

    public DataItem(int rowID, int id, String mmac, int rate, String wssid, String wmac, Timestamp date, float lat, float lon, String addr) {
        this.rowID = rowID;
        this.id = id;
        this.mmac = mmac;
        this.rate = rate;
        this.wssid = wssid;
        this.wmac = wmac;
        this.date = date;
        this.lat = lat;
        this.lon = lon;
        this.addr = addr;
    }

    public int getRowID() {
        return rowID;
    }

    public void setRowID(int rowID) {
        this.rowID = rowID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMmac() {
        return mmac;
    }

    public void setMmac(String mmac) {
        this.mmac = mmac;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getWssid() {
        return wssid;
    }

    public void setWssid(String wssid) {
        this.wssid = wssid;
    }

    public String getWmac() {
        return wmac;
    }

    public void setWmac(String wmac) {
        this.wmac = wmac;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "DataItem{" +
                "rowID=" + rowID +
                ", id=" + id +
                ", mmac='" + mmac + '\'' +
                ", rate=" + rate +
                ", wssid='" + wssid + '\'' +
                ", wmac='" + wmac + '\'' +
                ", date=" + date +
                ", lat=" + lat +
                ", lon=" + lon +
                ", addr='" + addr + '\'' +
                '}';
    }
}
