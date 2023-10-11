package top.expli.schoolfish;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * 常规物品的交易订单类.
 */
@Entity
public class ItemOrder implements Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private final String sellerID;
    private final String buyerID;
    private final String itemID;
    private int orderStatus;
//    private ItemSnapshot itemSnapshot;

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 检查订单支付情况.
     * 底层使用 getOrderStatus
     *
     * @return 订单的支付情况，True为已支付、False为未支付
     */
    @Override
    public boolean isPaid() {
        switch (this.orderStatus){
            case OrderStats.PLACED:
            case OrderStats.CLOSED:
                return false;
            case OrderStats.PAID:
            case OrderStats.SHIPPED:
            case OrderStats.SIGNED:
            case OrderStats.NOT_REVIEW:
            case OrderStats.DONE:
            case OrderStats.AFTER_SALE:
                return true;
        }
        return false;
    }

    /**
     * Constructs an ItemOrder with the provided seller ID, buyer ID, item ID, and item snapshot.
     *
     * @param sellerID The seller's ID.
     * @param buyerID  The buyer's ID.
     * @param itemID   The ID of the item in the order.
     *                 //     * @param itemSnapshot The snapshot of the ordered item.
     */
    public ItemOrder(String sellerID, String buyerID, String itemID) {
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.itemID = itemID;
//        this.itemSnapshot = itemSnapshot;
        this.orderStatus = OrderStats.UNDEFINED;
    }

    protected ItemOrder() {

        this.sellerID = "@Undef";
        this.buyerID = "@Undef";
        this.itemID = "@Undef";
    }

    /**
     * Gets the seller's ID for this order.
     *
     * @return The seller's ID.
     */
    @Override
    public String getSellerID() {
        return sellerID;
    }

    /**
     * 获取字符串形式的订单号
     * @return 字符串形式的订单号，最后的 @ 之后的数字代表订单类型
     */
    @Override
    public String getOrderID() {
        return String.valueOf(this.id) + String.format("@%d", OrderTypes.ITEM_ORDER);
    }

    /**
     * Gets the buyer's ID for this order.
     *
     * @return The buyer's ID.
     */
    @Override
    public String getBuyerID() {
        return buyerID;
    }

    /**
     * Gets the item's ID for this order.
     *
     * @return The item's ID.
     */
    @Override
    public String getItemID() {
        return itemID;
    }

    /**
     * Gets the snapshot of the ordered item.
     */
    @Override
    public void getItemSnapshot() {
        // Implement the logic to retrieve the item snapshot here.
        // You can call methods on the ItemSnapshot object to retrieve item information.
    }

    /**
     * Gets the order status for this item order.
     *
     * @return The order status.
     */
    @Override
    public int getOrderStatus() {
        return orderStatus;
    }
}
