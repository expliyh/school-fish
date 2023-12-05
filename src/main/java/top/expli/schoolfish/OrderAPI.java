package top.expli.schoolfish;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;

import java.lang.runtime.ObjectMethods;
import java.util.List;

import lombok.Data;

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
}

@Data
class GroupedOrderResponse {
    public int status;
    public int record_num;
    public List<ItemOrder> orders;
    public String message;
}

