package my.example.ampq.web;

import my.example.ampq.ProducerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.util.Collections.singletonMap;

@RestController
public class MessageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    private final AmqpTemplate amqpTemplate;

    public MessageController(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    @RequestMapping(
            path = "/sendMessage",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST
    )
    public Map<String, Object> sendMessage(@RequestBody Request request) {
        LOGGER.info("Request received: {}", request);

        amqpTemplate.convertAndSend(
                ProducerConstants.EXCHANGE_NAME,
                request.getRoutingKey(),
                singletonMap("message", request.getMessage())
        );

        return singletonMap("success", true);
    }
}