package top.expli.schoolfish;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;

import java.lang.runtime.ObjectMethods;
import java.util.List;
import java.util.Objects;

import lombok.Data;
import top.expli.schoolfish.exceptions.IDFormatInvalid;
import top.expli.schoolfish.exceptions.OrderNotFound;

public class OrderAPI {
    public static String myOrder(String uid, String filter, int page, int num_per_page) throws JsonProcessingException {
        GroupedOrderResponse response = new GroupedOrderResponse();
        response.status = 200;
        Page<ItemOrder> orders = Database.getItemOrderByPageAsAll(uid, filter, page, num_per_page);
        response.orders = orders.toList();
        response.record_num = (int) orders.getTotalElements();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(response);
    }

    public static String getOrder(String order_id) throws JsonProcessingException {
        SingleOrderResponse response = new SingleOrderResponse();
        response.status = 200;
        try {
            response.order = Database.getOrder(order_id);
        } catch (IDFormatInvalid e) {
            response.status = 400;
            response.message = "Invalid OrderId Format!";
        } catch (OrderNotFound e) {
            response.status = 404;
            response.message = "Order Not Found!";
        }
        System.out.println(response.order);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(response);
    }

    public static String getWaitForShip(String uid) throws JsonProcessingException {
        GroupedOrderResponse response = new GroupedOrderResponse();
        response.status = 200;
        response.orders = Database.getMyWaitForShip(uid);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(response);
    }

    public static String confirm(String uid, String orderID) throws JsonProcessingException {
        OrderPayLinkResponse response = new OrderPayLinkResponse();
        response.status = 200;
        ItemOrder order;
        try {
            order = Database.getOrder(orderID);
            if (order.getOrderStatus() != 0 && order.getOrderStatus() != 1 && order.getOrderStatus() != OrderStats.SHIPPED) {
                response.status = 400;
                response.message = "订单不能确认！";
            } else if (!Objects.equals(order.getBuyerID(), uid)) {
                response.status = 400;
                response.message = "这不是你的订单！";
            } else {
//                此处使用支付平台SDK生成支付链接

                order.setOrderStatus(OrderStats.NOT_REVIEW);
                Database.updateOrder(order);
            }
        } catch (IDFormatInvalid e) {
            response.status = 400;
            response.message = "Invalid OrderId Format!";
        } catch (OrderNotFound e) {
            response.status = 404;
            response.message = "Order Not Found!";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(response);
    }

    public static String ship(String uid, String orderID, String trackingNumber) throws JsonProcessingException {
        OrderPayLinkResponse response = new OrderPayLinkResponse();
        response.status = 200;
        ItemOrder order;
        try {
            order = Database.getOrder(orderID);
            if (order.getOrderStatus() != OrderStats.PAID) {
                response.status = 400;
                response.message = "订单不能发货！";
            } else if (!Objects.equals(order.getSellerID(), uid)) {
                response.status = 400;
                response.message = "这不是你的订单！";
            } else {
                order.setTrackingNumber(trackingNumber);
                order.setOrderStatus(OrderStats.SHIPPED);
                Database.updateOrder(order);
            }
        } catch (IDFormatInvalid e) {
            response.status = 400;
            response.message = "无效的订单号！";
        } catch (OrderNotFound e) {
            response.status = 404;
            response.message = "订单不存在！";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(response);
    }

    public static String getPayLink(String uid, String orderID) throws JsonProcessingException {
        OrderPayLinkResponse response = new OrderPayLinkResponse();
        response.status = 200;
        ItemOrder order;
        try {
            order = Database.getOrder(orderID);
            if (order.getOrderStatus() != 0) {
                response.status = 400;
                response.message = "订单不能支付！";
            } else if (!Objects.equals(order.getBuyerID(), uid)) {
                response.status = 400;
                response.message = "这不是你的订单！";
            } else {
//                此处使用支付平台SDK生成支付链接
                response.status = 200;
                response.payLink = "http://localhost:8080/api/pay?token=12345678&order_id=" + orderID;
            }
        } catch (IDFormatInvalid e) {
            response.status = 400;
            response.message = "Invalid OrderId Format!";
        } catch (OrderNotFound e) {
            response.status = 404;
            response.message = "Order Not Found!";
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(response);
    }
}

@Data
class GroupedOrderResponse {
    public int status;
    public int record_num;
    public List<ItemOrder> orders;
    public String message;
}

@Data
class SingleOrderResponse {
    public int status;
    public ItemOrder order;
    public String message;
}

@Data
class OrderPayLinkResponse {
    public int status;
    public String payLink;
    public String message;
}

