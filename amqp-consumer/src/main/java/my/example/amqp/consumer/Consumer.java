package my.example.amqp.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class Consumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

    @Autowired
    private ConfigurableApplicationContext ctx;

    @RabbitListener(queues = ConsumerConstants.QUEUE_NAME1)
    public void listerQueue1(@Payload Map<String, String> messageBody) {
        String message = messageBody.get("message");
        LOGGER.info("Message from queue1: {}", message);

        if (message.equals("exit")) {
            ctx.close();
        }
    }

    @RabbitListener(queues = ConsumerConstants.QUEUE_NAME2)
    public void listerQueue2(@Payload Map<String, String> messageBody) {
        String message = messageBody.get("message");
        LOGGER.info("Message from queue2: {}", message);

        if (message.equals("exit")) {
            ctx.close();
        }
    }
}
