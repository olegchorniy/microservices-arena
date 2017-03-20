package my.example.time;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Date;

@RestController
public class TimeController {

    @RequestMapping("/time")
    public String time() {

        System.out.println("[Serve 'time' request]: " + new Date());
        return LocalTime.now().toString();
    }
}
