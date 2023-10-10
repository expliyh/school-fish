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
    public int getOrderStat() {
        return orderStatus;
    }
}
