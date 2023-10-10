package top.expli.schoolfish;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HTTP API 接口的路由类，在此处配置 API 路由.
 */
@Controller
public class ApiController {
    /**
     * 下订单的路由，返回字符串形式的订单号.
     * @param itemID 与订单关联的物品的ID
     * @param buyerID 买家的UID
     * @param sellerID 卖家的UID
     * @param token 当前登录用户的 access_token 用于鉴权
     * @return 字符串形式的订单号
     */
    @PostMapping("/api/place_order")
    public String placeOrderController(@RequestParam String itemID,
                                       @RequestParam String buyerID,
                                       @RequestParam String sellerID,
                                       @RequestParam String token) {
//        Token Verify Here
        return OrderManager.placeOrder(itemID, sellerID, buyerID);
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
