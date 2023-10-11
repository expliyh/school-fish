//该类为订单状态与整型的对应，在程序中任何使用订单状态
//的位置都应使用该类，不应直接使用整型。

package top.expli.schoolfish;

public class OrderStats {
    /**
     * 未定义的订单状态
     */
    public static final int UNDEFINED = -1;
    /**
     * 已下单未支付
     */
    public static final int PLACED = 0;
    /**
     * 订单已支付
     */
    public static final int PAID = 1;
    /**
     * 订单已发货
     */
    public static final int SHIPPED = 2;
    /**
     * 订单已签收
     */
    public static final int SIGNED = 3;
    /**
     * 订单已完成但未评价
     */
    public static final int NOT_REVIEW = 4;
    /**
     * 订单已完成并评价
     */
    public static final int DONE = 5;
    /**
     * 订单关闭 (未完成)
     */
    public static final int CLOSED = 100;
    /**
     * 订单售后中
     */
    public static final int AFTER_SALE = 200;
}
