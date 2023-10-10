package top.expli.schoolfish.exceptions;

/**
 * 找不到支付订单异常
 */
public class PaymentNotFound extends Exception {
    public PaymentNotFound() {
        super();
    }

    PaymentNotFound(String message) {
        super(message);
    }
}
