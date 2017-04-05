package my.example.datetime;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/*
Fallback is not being autowired instead of generated client,
because the latter is registered with 'primary' flag in the application context.
*/
@Component
public class TimeClientFallback implements TimeFeignClient {

    @Override
    public Map<String, String> getTime() {
        return Collections.singletonMap("time", "[Time information not available]");
    }
}
