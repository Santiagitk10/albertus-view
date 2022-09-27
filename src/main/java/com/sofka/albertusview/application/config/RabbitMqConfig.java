package com.sofka.albertusview.application.config;



import com.sofka.albertusview.application.handlers.QueueHandler;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class RabbitMqConfig {
    public static final String EXCHANGE = "core-blockchain";
    public static final String PROXY_QUEUE_APPLICATION = "events.proxy.application";
    public static final String GENERAL_QUEUE = "events.blockchain-general";

    public static final String PROXY_ROUTING_KEY_APPLICATION = "routingKey.proxy.application";

    @Autowired
    private QueueHandler handler;

    @Bean
    public Queue applicationQueue(){
        return new Queue(PROXY_QUEUE_APPLICATION);
    }

    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding BindingToApplicationQueue() {
        return BindingBuilder.bind(applicationQueue()).to(getTopicExchange()).with(PROXY_ROUTING_KEY_APPLICATION);
    }



    @RabbitListener(queues = GENERAL_QUEUE)
    public void listenToGeneralQueue(String received){
        /**Starting point*/
        handler.accept(received);
    }


}
