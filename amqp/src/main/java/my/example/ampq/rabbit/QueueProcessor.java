package my.example.ampq.rabbit;

import my.example.ampq.Constants;
import my.example.ampq.web.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author olch0615
 *         Date: 3/23/2017
 *         Time: 5:42 PM
 */
@Component
public class QueueProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(QueueProcessor.class);

    @RabbitListener(queues = Constants.QUEUE_NAME)
    public void processMessage(Request request) {
        LOGGER.info("Message received from the queue: {}", request);
    }
}