package my.example.datetime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DateTimeController {

    @Autowired
    private RestTemplate template;

    @Autowired
    private TimeFeignClient timeClient;

    @Autowired
    private ApplicationContext ctx;

    @RequestMapping("/dateTime")
    public String dateTime() {
        //String time = template.getForObject("http://TIME-SERVICE/time", String.class);

        String time = timeClient.getTime();
        String date = template.getForObject("http://DATE-SERVICE/date", String.class);

        return date + " - " + time;
    }
}
