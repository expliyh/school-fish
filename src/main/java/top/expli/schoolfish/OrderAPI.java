package top.expli.schoolfish;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;

import java.lang.runtime.ObjectMethods;
import java.util.List;

import lombok.Data;
import top.expli.schoolfish.exceptions.IDFormatInvalid;
import top.expli.schoolfish.exceptions.OrderNotFound;

public class OrderAPI {
    public static String myOrder(String uid, int page, int num_per_page) throws JsonProcessingException {
        GroupedOrderResponse response = new GroupedOrderResponse();
        response.status = 200;
        Page<ItemOrder> orders = Database.getItemOrderByPageAsAll(uid, page, num_per_page);
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

