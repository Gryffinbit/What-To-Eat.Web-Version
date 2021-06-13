package entity;

import java.sql.Timestamp;

public class PublicFoods {
    private int fid;
    private String foodName;
    private String area;
    private String minNum;
    private String maxNum;
    private String minPrice;
    private String maxPrice;
    private String submitter;
    private boolean verify;
    private Timestamp modifyTime;

    public int getFid() {
        return fid;
    }

    public String getArea() {
        return area;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getMaxNum() {
        return maxNum;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public String getMinNum() {
        return minNum;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public String getSubmitter() {
        return submitter;
    }

    public Timestamp getModifyTime() {
        return modifyTime;
    }

    public boolean isVerify() {
        return verify;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setMaxNum(String maxNum) {
        this.maxNum = maxNum;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinNum(String minNum) {
        this.minNum = minNum;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public void setModifyTime(Timestamp modifyTime) {
        this.modifyTime = modifyTime;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public void setVerify(boolean verify) {
        this.verify = verify;
    }
}

