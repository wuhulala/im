package com.wuhulala;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.wuhulala.domain.Message;
import com.wuhulala.domain.Response;
import com.wuhulala.domain.WiselyMessage;
import com.wuhulala.domain.WiselyResponse;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xueaohui
 */
@Controller
@EnableScheduling
public class WsController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Scheduled(cron = "0/5 * * * * ? ")
    public void say() throws InterruptedException, JsonProcessingException {
        List userLists = listLoggedInUsers();
        System.out.println("5秒刷新当前登陆的用户 : [ " + userLists + " ]");
        //messagingTemplate.convertAndSend("/topic/users", new ObjectMapper().writeValueAsString(userLists));
    }

    @MessageMapping("/chat")
    public void handlerChat(Principal principal, String msg) throws IOException {
        Date date = new Date();
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(msg, Message.class);
        if (message.getName().equals("system")) {
            if(message.getText().equals("disconnect")) {
               /* System.out.println("发送给系统的消息:" + session.getId());
                sessionRegistry.removeSessionInformation(session.getId());
                */System.out.println(principal.getName() + "下线了");
            }
        } else {
            mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
            String sender = principal.getName();
            messagingTemplate.convertAndSendToUser(message.getName(), "/queue/notifications",mapper.writeValueAsString(new Response(sender,date,message.getText())));
        }
    }


    @Autowired
    private SessionRegistry sessionRegistry;

    @RequestMapping("/now")
    @ResponseBody
    public List nowUser(HttpSession session) {
        session.setAttribute("sessionId",session.getId());
        return listLoggedInUsers();
    }

    public List listLoggedInUsers() {
        final List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        List<String> results = new ArrayList<>();
        for (Object o : allPrincipals) {
            User user = (User) o;
            results.add(user.getUsername());
        }
        return results;
    }

    @RequestMapping("/logout")
    @ResponseBody
    public String logout(HttpSession session){
        String id = session.getId();
        sessionRegistry.removeSessionInformation(id);
        return "logout success";
    }




}
