package com.wuhulala;

import com.wuhulala.websocket.MyChannelInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * @author xueaohui
 *
 * @description 开启Stomp协议来传输基于代理的消息，这是控制器支持使用@MessageMapping 就像使用@RequestMapping一样
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketMessageBrokerConfig extends AbstractWebSocketMessageBrokerConfigurer {
    /**
     * 注册STOMP协议的节点，并映射指定的URL
     * @param registry
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册一个STOMP的endpoint，并指定使用sockJS协议
        registry.addEndpoint("/endpointWisely").withSockJS();
        //注册一个STOMP的endpoint，并指定使用sockJS协议
        registry.addEndpoint("/endpointChat").withSockJS();

    }

    /**
     * 配置消息代理
     * @param registry
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        //广播式应配置一个/topic消息代理
        registry.enableSimpleBroker("/queue","/topic", "/user");

        registry.setUserDestinationPrefix("/user");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(new MyChannelInterceptor());
    }
}
