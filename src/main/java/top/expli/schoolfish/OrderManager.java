package top.expli.schoolfish;

/**
 * 管理订单的工具类.
 */
public class OrderManager {
    /**
     * 下订单的函数.
     * 在请求 HTTP 下订单 API 时会调用此函数。
     *
     * @param itemID   订单交易物品的 ID
     * @param sellerID 卖家的 UID
     * @param buyerID  买家的 UID
     * @return 字符串形式的订单号，最后的 @ 之后的数字代表订单类型
     */
    public static String placeOrder(String itemID, String sellerID, String buyerID) {
        int itemType = 0;
        Order order;
        String orderID = "";
        switch (itemType) {
            case 0 -> {
                order = new ItemOrder(sellerID, buyerID, itemID);
                order.setOrderStatus(OrderStats.PLACED);
                orderID = Database.addItemOrder((ItemOrder) order);
            }

        }
        return orderID;
    }
}
