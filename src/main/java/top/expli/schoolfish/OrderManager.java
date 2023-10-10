package top.expli.schoolfish;

public class OrderManager {
    public static String placeOrder(String itemID, String sellerID, String buyerID) {
        int itemType = 0;
        Order order;
        String orderID = "";
        switch (itemType) {
            case 0:
                order = new ItemOrder(sellerID, buyerID, itemID);
                order.setOrderStatus(OrderStats.PLACED);
                orderID = Database.addItemOrder((ItemOrder) order);
        }
        return orderID;
    }
}
