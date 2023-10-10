package top.expli.schoolfish;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * HTTP API 接口的路由类，在此处配置 API 路由.
 */
@Controller
public class ApiController {
    /**
     * 服务端程序测试路由，如果在运行就会返回 Hello, World!
     * @return 返回 Hello, World! 证明服务端程序正在运行
     */
    @RequestMapping({"/hello", "/ping"})
    @ResponseBody
    public String hello() {
        return "Hello, World!";
    }
}
