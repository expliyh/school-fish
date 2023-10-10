package top.expli.schoolfish;

/**
 * 订单接口
 */
public interface Order {
    /**
     * Get seller's UserID.
     *
     * @return Seller's ID
     * @author expliyh
     * @since 0.0
     */
    String getSellerID();

    /**
     * Get buyer's UserID.
     *
     * @return Buyer's ID
     * @author expliyh
     * @since 0.0
     */
    String getOrderID();

    String getBuyerID();

    String getItemID();

    void getItemSnapshot();

    int getOrderStat();

    void setOrderStatus(int orderStatus);
}
