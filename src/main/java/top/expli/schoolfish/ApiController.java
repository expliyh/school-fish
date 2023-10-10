package top.expli.schoolfish;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApiController {
    @RequestMapping({"/hello", "/ping"})
    @ResponseBody
    public String hello() {
        return "Hello, World!";
    }
}
