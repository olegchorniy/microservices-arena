package my.example.webui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UiController {

    @RequestMapping("/main")
    public String main(Model model) {
        model.addAttribute("user", "Oleg");
        return "main";
    }
}
