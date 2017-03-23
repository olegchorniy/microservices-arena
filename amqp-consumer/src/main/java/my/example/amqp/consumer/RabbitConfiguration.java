package my.example.amqp.consumer;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer,
            ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        factory.setMessageConverter(messageConverter());

        return factory;
    }

    @Bean
    public Queue myQueue1() {
        return new Queue(ConsumerConstants.QUEUE_NAME1, false, false, false, null);
    }

    @Bean
    public Queue myQueue2() {
        return new Queue(ConsumerConstants.QUEUE_NAME2, false, false, false, null);
    }

    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(myQueue1()).to(myExchange()).with("my.*").noargs();
    }

    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(myQueue2()).to(myExchange()).with("*.key").noargs();
    }

    @Bean
    public Exchange myExchange() {
        return new TopicExchange(ConsumerConstants.EXCHANGE_NAME, false, false, null);
    }
}
