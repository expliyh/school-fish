package top.expli.schoolfish;

import java.util.HashMap;

/**
 * 用于支付的工具类
 */
public class Payments {
    private static HashMap<String, Payment> payments;

}

class Payment {
    private String platformID;
    private String orderID;
    private String sign;

    private static final long TIMEOUT = 15 * 60 * 1000; // 15 minutes

    private long createTime;

    public boolean isTimedOut() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - createTime) > TIMEOUT;
    }

    private void resetTimeout() {
        createTime = System.currentTimeMillis();
    }

    public Payment(String platformID, String orderID, String sign) {
        this.platformID = platformID;
        this.orderID = orderID;
        this.sign = sign;
        this.resetTimeout();
    }

    public String getPlatformID() {
        return platformID;
    }

    public void setPlatformID(String platformID) {
        this.platformID = platformID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}