package my.example.datetime;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "time-service", fallback = TimeClientFallback.class)
public interface TimeFeignClient {

    @RequestMapping(method = RequestMethod.GET, path = "/time", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Map<String, String> getTime();
}
