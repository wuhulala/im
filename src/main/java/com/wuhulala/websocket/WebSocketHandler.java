
package com.wuhulala.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author wuhulala
 */
public class WebSocketHandler extends TextWebSocketHandler {
	
	final static Logger LOG  = LoggerFactory.getLogger(WebSocketHandler.class);
	final static String WEBSOCKET_USERNAME = "userName";
	
	private static ArrayList<WebSocketSession> users = new ArrayList<WebSocketSession>();
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) {
		// 
		LOG.info("text message: {} - {}",session.getId(),message.getPayload());
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// 建立连接后的处理
		LOG.info("client :"+session.getId() +"connect success!");
		
		users.add(session);
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		// 
		System.out.println("handleTransportError");
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		//
		System.out.println("afterConnectionClosed");
		users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}
	
	public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {  
                if (user.isOpen()) {  
                    user.sendMessage(message);  
                }  
            } catch (IOException e) {  
                LOG.error("",e);
            }  
        }  
    }
	
	/** 
     * 给某个用户发送消息 
     * 
     * @param userName 
     * @param message 
     */  
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get(WEBSOCKET_USERNAME).equals(userName)) {  
                try {  
                    if (user.isOpen()) {  
                        user.sendMessage(message);  
                    }  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
                break;  
            }  
        }  
    }  
}  
