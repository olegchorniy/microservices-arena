package my.example.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal")
public class InternalController {

    @RequestMapping("/local")
    public String internalEndpoint() {
        return "H1, I'am local endpoint: " + System.currentTimeMillis();
    }
}
