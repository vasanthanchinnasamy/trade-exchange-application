package com.innovate.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfiguration {

    public static final String MATCH_QUEUE = "exchangesystem_match_queue";
    public static final String TRADE_QUEUE = "exchangesystem_trade_queue";
    public static final String EXCHANGE = "exchangesystem_exchange";
    public static final String MATCH_ROUTING_KEY = "exchangesystem_match_routingKey";
    public static final String TRADE_ROUTING_KEY = "exchangesystem_trade_routingKey";

    @Bean
    public Queue matchQueue() {
        return new Queue(MATCH_QUEUE);
    }
    
    @Bean
    public Queue tradeQueue() {
        return new Queue(TRADE_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding matchBinding(@Qualifier("matchQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(MATCH_ROUTING_KEY);
    }
    
    @Bean
    public Binding tradeBinding(@Qualifier("tradeQueue") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(TRADE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}