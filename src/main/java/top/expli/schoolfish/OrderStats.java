//该类为订单状态与整型的对应，在程序中任何使用订单状态
//的位置都应使用该类，不应直接使用整型。

package top.expli.schoolfish;

public class OrderStats {
    public static final int PLACED = 0;
    public static final int PAID = 1;
    public static final int SHIPPED = 2;
    public static final int SIGNED = 3;
    public static final int NOT_REVIEW = 4;
    public static final int DONE = 5;
    public static final int CLOSED=100;
    public static final int AFTER_SALE=200;
}
