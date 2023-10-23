package top.expli.schoolfish;

import top.expli.schoolfish.exceptions.IDFormatInvalid;
import top.expli.schoolfish.exceptions.OrderNotFound;

/**
 * 用于返回Web请求的订单类，添加了文字形式的信息
 */
public class OrderForDisplay {
    public OrderForDisplay(String orderId) throws IDFormatInvalid, OrderNotFound {
        this.orderType = OrderManager.getOrderType(orderId);
        switch (this.orderType){
            case OrderTypes.ITEM_ORDER -> {
                this.orderTypeStr="商品订单";
                ItemOrder order= (ItemOrder) OrderManager.getOrder(orderId);
                this.buyerId=order.getBuyerID();
                this.sellerId=order.getSellerID();
            }
            case OrderTypes.RECHARGE -> {
                this.orderTypeStr="充值";
            }
        }
    }

    String itemName;
    String itemTypeStr;
    int itemType;
    String orderTypeStr;
    int orderType;
    String orderId;
    String buyerName;
    String buyerId;
    String buyerProfile;
    String sellerId;
    String sellerName;
    String sellerProfile;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemTypeStr() {
        return itemTypeStr;
    }

    public void setItemTypeStr(String itemTypeStr) {
        this.itemTypeStr = itemTypeStr;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerProfile() {
        return buyerProfile;
    }

    public void setBuyerProfile(String buyerProfile) {
        this.buyerProfile = buyerProfile;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerProfile() {
        return sellerProfile;
    }

    public void setSellerProfile(String sellerProfile) {
        this.sellerProfile = sellerProfile;
    }
}
