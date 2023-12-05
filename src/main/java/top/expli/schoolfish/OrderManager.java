package top.expli.schoolfish;

import top.expli.schoolfish.exceptions.IDFormatInvalid;
import top.expli.schoolfish.exceptions.OrderNotFound;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                //此处获取商品价格
                double itemPrice = 0;
                String itemName = "TestName";
                order = new ItemOrder(sellerID, buyerID, itemID, itemPrice, itemName);
                order.setOrderStatus(OrderStats.PLACED);
                orderID = Database.addItemOrder((ItemOrder) order);
            }

        }
        return orderID;
    }

    /**
     * 根据订单号解析订单类型
     *
     * @param orderID 字符串形式的订单号
     * @return 整型的订单类型
     * @throws IDFormatInvalid 订单号格式错误
     */
    public static int getOrderType(String orderID) throws IDFormatInvalid {
        String pattern = "(.+)@(.+)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(orderID);
        int orderType = -1;
        if (m.find()) {
            try {
                orderType = Integer.parseInt(m.group(2));
            } catch (NumberFormatException numberFormatException) {
                throw new IDFormatInvalid("无效的订单号");
            }
        }
        return orderType;
    }

    /**
     * 根据订单号获取订单对象，只是重新打包了 Database.getOrder
     *
     * @param orderId 字符串形式的订单号
     * @return 订单对象
     * @throws IDFormatInvalid 订单号格式错误
     * @throws OrderNotFound   找不到订单
     */
    public static Order getOrder(String orderId) throws IDFormatInvalid, OrderNotFound {
        return Database.getOrder(orderId);
    }
}
