package my.example.datetime;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "time-service", fallback = TimeClientFallback.class)
public interface TimeFeignClient {

    @RequestMapping(method = RequestMethod.GET, path = "/time")
    String getTime();
}
