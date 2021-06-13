package entity;

import java.sql.Timestamp;

public class OrderHistory {
    private int id;
    private int uid;
    private int fid;
    private Timestamp orderTime;
    private boolean isPrivateMenu;

    public void setId(int id) {
        this.id = id;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public void setPrivateMenu(boolean privateMenu) {
        isPrivateMenu = privateMenu;
    }

    public int getUid() {
        return uid;
    }

    public int getId() {
        return id;
    }

    public int getFid() {
        return fid;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public boolean isPrivateMenu() {
        return isPrivateMenu;
    }
}
