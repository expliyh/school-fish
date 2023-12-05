package top.expli.schoolfish;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


/**
 * HTTP API 接口的路由类，在此处配置 API 路由.
 */
@Controller
public class ApiController {

    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 下订单的路由，返回字符串形式的订单号.
     *
     * @param itemID   与订单关联的物品的ID
     * @param buyerID  买家的UID
     * @param sellerID 卖家的UID
     * @param token    当前登录用户的 access_token 用于鉴权
     * @return 字符串形式的订单号
     */
    @CrossOrigin(originPatterns = "*", allowCredentials = "true", maxAge = 3600)
    @PostMapping("/api/place_order")
    @ResponseBody
    public String placeOrderController(@RequestParam String itemID,
                                       @RequestParam String buyerID,
                                       @RequestParam String sellerID,
                                       @RequestParam String token) {
//        Token Verify Here
        return OrderManager.placeOrder(itemID, sellerID, buyerID);
    }

    @PostMapping("/api/get_order_page")
    @ResponseBody
    @CrossOrigin(originPatterns = "*", allowCredentials = "true", maxAge = 3600)
    public String getOrderPage(
            @RequestParam String token,
            @RequestParam int page_count,
            @RequestParam int per_page
    ) throws JsonProcessingException {
        System.out.println(token);
        System.out.println(OrderAPI.myOrder(token, page_count, per_page));
        return OrderAPI.myOrder(token, page_count, per_page);
    }

    @PostMapping("/api/get_order")
    @ResponseBody
    @CrossOrigin(originPatterns = "*", allowCredentials = "true", maxAge = 3600)
    public String getOrder(
            @RequestParam String token,
            @RequestParam String order_id
    ) throws JsonProcessingException {
        return OrderAPI.getOrder(order_id);
    }

    @GetMapping("/mkorder")
    @ResponseBody
    public String make_order() {
        ItemOrder order = new ItemOrder("12345678", "23456789", "1234567890", 0, "TestName");
        Database.addItemOrder(order);
        return "Success";
    }

    /**
     * 服务端程序测试路由，如果在运行就会返回 Hello, World!
     *
     * @return 返回 Hello, World! 证明服务端程序正在运行
     */
    @RequestMapping({"/hello", "/ping"})
    @ResponseBody
    public String hello() {
        return "Hello, World!";
    }
}
