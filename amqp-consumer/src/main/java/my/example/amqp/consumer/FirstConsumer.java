package my.example.amqp.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author olch0615
 *         Date: 3/24/2017
 *         Time: 11:13 AM
 */
@Component
@Profile("first")
public class FirstConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(FirstConsumer.class);

    @Autowired
    private ConfigurableApplicationContext ctx;

    @PostConstruct
    public void init() {
        LOGGER.info("Created");
    }

    @RabbitListener(queues = ConsumerConstants.QUEUE_NAME1)
    public void listerQueue1(@Payload Map<String, String> messageBody) {
        String message = messageBody.get("message");
        LOGGER.info("Message from queue1: {}", message);
    }
}