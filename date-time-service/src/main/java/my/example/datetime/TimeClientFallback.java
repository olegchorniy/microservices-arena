package my.example.datetime;

import org.springframework.stereotype.Component;

@Component
public class TimeClientFallback implements TimeFeignClient {

    @Override
    public String getTime() {
        return "[Time information not available]";
    }
}
