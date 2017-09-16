package au.com.tla.flight.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {

    @RequestMapping("/hello")
    public String helloWorld(Model model) {
        model.addAttribute("message", "Hello World!");
        return "hello";
    }
}