package top.expli.schoolfish;

import top.expli.schoolfish.exceptions.*;

import java.util.HashMap;
import java.util.Objects;

/**
 * 用于支付的工具类
 */
public class Payments {
    private static HashMap<String, Payment> payments;

    /**
     * 发起支付。
     *
     * @param orderID 本平台的订单号
     * @return 支付平台的订单号
     */
    public String startPayment(String orderID) {
        return "";
    }

    /**
     * 处理支付回调的方法.
     * @param platformID 支付平台的订单号
     * @param sign 支付平台的签名
     * @return 成功时返回 0
     * @throws PaymentNotFound 当找不到支付订单时返回，可能是由于支付期间服务器重启或订单无效，应提醒客户联系客服
     * @throws PaymentNotSign 订单签名错误，可能是恶意请求冒充正确的支付平台
     * @throws IDFormatInvalid 支付订单保存的系统订单号无效
     * @throws OrderNotFound 找不到系统订单号
     * @throws InvalidPayment 支付订单无效，可能是由于订单不是待支付状态
     */
    public int pay(String platformID, String sign) throws PaymentNotFound, PaymentNotSign, IDFormatInvalid, OrderNotFound, InvalidPayment {
        if (!payments.containsKey(platformID)) {
            throw new PaymentNotFound();
        }
        Payment payment = payments.get(platformID);
        if (!Objects.equals(sign, payment.getSign())) {
            throw new PaymentNotSign("签名不正确");
        }
        Order order = Database.getOrder(payment.getOrderID());

//        判断订单是否是待支付状态
        if (order.getOrderStat() != OrderStats.PLACED) {
            throw new InvalidPayment("订单不是待支付状态");
        }

//        修改订单状态并保存
        order.setOrderStatus(OrderStats.PAID);
        Database.updateOrder(order);
        return 0;
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
     *
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
     *
     * @param platformID 支付平台的订单号.
     * @param orderID    本平台的订单号.
     * @param sign       支付平台签名，用于验证回调是否合法.
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