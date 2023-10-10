package top.expli.schoolfish.exceptions;

/**
 * 支付回调未签名或签名错误异常
 */
public class PaymentNotSign extends Exception {
    public PaymentNotSign() {
        super();
    }

    public PaymentNotSign(String message) {
        super(message);
    }
}
