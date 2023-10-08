package top.expli.schoolfish;


public interface Order {
    /**
     * Get seller's UserID.
     * @author expliyh
     * @return Seller's ID
     * @since 0.0
     */
    String getSellerID();
    /**
     * Get buyer's UserID.
     * @author expliyh
     * @return Buyer's ID
     * @since 0.0
     */
    String getBuyerID();

    String getItemID();

    void getItemSnapshot();

    int getOrderStat();
}
