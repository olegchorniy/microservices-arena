package my.example;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class FirstController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private EurekaClient eurekaClient;

    @RequestMapping(path = "/testJson", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<String, String> testJson() {
        return Collections.singletonMap("key", "va-lue");
    }

    @RequestMapping(path = "/service/{serviceName}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<ServiceInstance> queryService(@PathVariable("serviceName") String serviceName) {
        return discoveryClient.getInstances(serviceName);
    }
}
