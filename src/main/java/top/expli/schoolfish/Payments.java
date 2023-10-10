package top.expli.schoolfish;

import java.util.HashMap;

/**
 * 用于支付的工具类
 */
public class Payments {
    private static HashMap<String, Payment> payments;

    /**
     * 发起支付。
     * @param orderID 本平台的订单号
     * @return 支付平台的订单号
     */
    public String startPayment(String orderID){
        return "";
    }
}

class Payment {
    /**
     * 支付平台的订单号.
     */
    private String platformID;
    /**
     * 本平台的订单号.
     */
    private String orderID;
    /**
     * 支付平台签名，用于验证回调是否合法.
     */
    private String sign;
    /**
     * 订单超时时间，默认15分钟.
     */
    private static final long TIMEOUT = 15 * 60 * 1000; // 15 minutes
    /**
     * 订单创建时间，使用 System.currentTimeMillis() 获取.
     * 该值应与支付平台保持一致或略大于支付平台设置.
     */
    private long createTime;

    /**
     * 判断订单是否超时.
     * @return 订单是否超时，超时为 True，未超时为 False.
     */
    public boolean isTimedOut() {
        long currentTime = System.currentTimeMillis();
        return (currentTime - createTime) > TIMEOUT;
    }

    /**
     * 重设超时时间。
     */
    private void resetTimeout() {
        createTime = System.currentTimeMillis();
    }

    /**
     * 构造函数.s
     * @param platformID 支付平台的订单号.
     * @param orderID 本平台的订单号.
     * @param sign 支付平台签名，用于验证回调是否合法.
     */
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