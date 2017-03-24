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

@Component
@Profile("second")
public class SecondConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecondConsumer.class);

    @Autowired
    private ConfigurableApplicationContext ctx;

    @PostConstruct
    public void init() {
        LOGGER.info("Created");
    }

    @RabbitListener(queues = ConsumerConstants.QUEUE_NAME2)
    public void listerQueue2(@Payload Map<String, String> messageBody) {
        String message = messageBody.get("message");
        LOGGER.info("Message from queue2: {}", message);
    }
}
