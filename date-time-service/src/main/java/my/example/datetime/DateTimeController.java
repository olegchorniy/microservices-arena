package my.example.datetime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DateTimeController {

    private final RestTemplate template;
    private final TimeFeignClient timeClient;

    @Autowired
    public DateTimeController(TimeFeignClient timeClient, RestTemplate template) {
        this.timeClient = timeClient;
        this.template = template;
    }

    @RequestMapping("/dateTime")
    public String dateTime() {

        String time = timeClient.getTime().get("time");

        //WARNING: rest template doesn't protected by RetryTemplate anymore.
        String date = template.getForObject("http://DATE-SERVICE/date", String.class);

        return date + " - " + time;
    }
}
