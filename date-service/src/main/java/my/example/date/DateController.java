package my.example.date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Date;

@RestController
public class DateController {

    @RequestMapping("/date")
    public String date() {
        System.out.println("[Serve 'date' request]: " + new Date());
        return LocalDate.now().toString();
    }
}
