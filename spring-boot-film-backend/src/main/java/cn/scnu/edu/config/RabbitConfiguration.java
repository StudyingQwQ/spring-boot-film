package cn.scnu.edu.config;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ消息队列配置
 */
@Configuration
public class RabbitConfiguration {
    @Bean("emailQueue")
    public Queue emailQueue(){
        return QueueBuilder
                .durable("mail")
                .build();
    }
    //消息转换器,将消息json序列化
    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}

