package top.expli.schoolfish;


public interface Order {
    String getSellerID();

    String getBuyerID();

    String getItemID();

    void getItemSnapshot();

    int getOrderStat();
}
