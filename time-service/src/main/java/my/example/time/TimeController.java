package my.example.time;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

@RestController
public class TimeController {

    @RequestMapping(path = "/time", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> time() {

        System.out.println("[Serve 'time' request]: " + new Date());
        return Collections.singletonMap("time", LocalTime.now().toString());
    }
}
