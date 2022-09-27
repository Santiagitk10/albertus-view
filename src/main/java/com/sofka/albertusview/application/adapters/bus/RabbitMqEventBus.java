package com.sofka.albertusview.application.adapters.bus;


import com.google.gson.Gson;
import com.sofka.albertusview.application.config.RabbitMqConfig;
import com.sofka.albertusview.business.gateways.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableRabbit
public class RabbitMqEventBus implements EventBus {

  private final RabbitTemplate rabbitTemplate;
  private final Gson gson = new Gson();


  public RabbitMqEventBus(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;

  }


  @Override
  public void publishApplication(Object model) {
    log.info(model.toString());
    rabbitTemplate.convertAndSend(
            RabbitMqConfig.PROXY_QUEUE_APPLICATION,
            gson.toJson(model).getBytes()
    );
  }

}
