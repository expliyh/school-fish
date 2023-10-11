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

    int getOrderStatus();

    void setOrderStatus(int orderStatus);

    /**
     * 检查订单支付情况.
     * 底层使用 getOrderStatus
     * @return 订单的支付情况，True为已支付、False为未支付
     */
    boolean isPaid();
}
