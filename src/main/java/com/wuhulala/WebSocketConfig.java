package com.wuhulala;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author xueaohui
 */

@Configuration
@EnableWebSocketMessageBroker //开启Stomp协议来传输基于代理的消息，这是控制器支持使用@MessageMapping 就像使用@RequestMapping一样
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //注册STOMP协议的节点，并映射指定的URL
        registry.addEndpoint("/endpointWisely").withSockJS();//注册一个STOMP的endpoint，并指定使用sockJS协议
        registry.addEndpoint("/endpointChat").withSockJS();//注册一个STOMP的endpoint，并指定使用sockJS协议

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理
        registry.enableSimpleBroker("/queue","/topic"); //广播式应配置一个/topic消息代理
    }
}
