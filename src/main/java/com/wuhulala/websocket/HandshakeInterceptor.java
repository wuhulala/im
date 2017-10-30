package com.wuhulala.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

/**
 * @author wuhulala
 */
public class HandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	@Override  
    public boolean beforeHandshake(ServerHttpRequest request,
                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                   Map<String, Object> attributes) throws Exception {
//        //得到握手传递的参数
//		Map<String,String> map = getParamMap(((ServletServerHttpRequest) request).getServletRequest());
//		Iterator<String> item = map.keySet().iterator();
//		while (item.hasNext()) {
//			String name = item.next();
//			attributes.put(name, map.get(name));
//		}
		return super.beforeHandshake(request, response, wsHandler, attributes);  
    }  
  
    @Override  
    public void afterHandshake(ServerHttpRequest request,
                               ServerHttpResponse response, WebSocketHandler wsHandler,
                               Exception ex) {
        System.out.println("After Handshake");  
        super.afterHandshake(request, response, wsHandler, ex);  
    }  
    
    
//    private static Map<String, String> getParamMap(HttpServletRequest request) {
//		Map<String, String> paramMap = new HashMap<String, String>();
//		@SuppressWarnings("unchecked")
//		Enumeration<String> enume = request.getParameterNames();
//		while (enume.hasMoreElements()) {
//			String paramName = enume.nextElement();
//			paramMap.put(paramName, request.getParameter(paramName));
//		}
//		return paramMap;
//	}
}
