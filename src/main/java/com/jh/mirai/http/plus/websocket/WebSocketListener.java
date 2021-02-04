package com.jh.mirai.http.plus.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jh.mirai.http.plus.annoation.Receive;
import com.jh.mirai.http.plus.domain.*;
import com.jh.mirai.http.plus.service.Mirai;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.Set;

@Component()
public class WebSocketListener {

    @Value("${websocket}")
    private String websocketUrl;

    @Value("${authKey}")
    private String authKey;

    @Resource
    Mirai mirai;

    @Bean
    public WebSocketClient webSocketClient() {
        try {
            mirai.auth(authKey); //认证获取session
            System.out.println("ws://"+websocketUrl+"/message?sessionKey="+System.getProperty("sessionKey"));
            WebSocketClient webSocketClient = new WebSocketClient(new URI("ws://"+websocketUrl+"/message?sessionKey="+System.getProperty("sessionKey")),new Draft_6455()) {

                Reflections reflections = new Reflections("com.jh.mirai.http.plus");//扫描指定包

                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    System.out.println("[websocket] 连接成功");
                }
                @Override
                public void onMessage(String message) {
                    Set<Class<?>> classes =  reflections.getTypesAnnotatedWith(Receive.class);//获取了标注哪些注解的类
                    JSONObject messageJson = JSON.parseObject(message);
                    String type = messageJson.getString("type");
                    String messageChain = messageJson.getString("messageChain"); //获取到消息链字符串
                    Long messageId = getMessageId(messageChain); //获取消息id
                    String context = getMessageContext(messageChain); //现阶段只对Plain处理
                    if(type.equals("FriendMessage")){//好友消息处理
                        FriendSender sender = JSONObject.parseObject(messageJson.getString("sender"),FriendSender.class);
                        /* 封装Message */
                        PrivateMessage privateMessage = new PrivateMessage();
                        privateMessage.setMessageId(messageId);
                        privateMessage.setMessage(context);
                        privateMessage.setMessageChain(messageChain);
                        privateMessage.setMessageType(type);
                        privateMessage.setSender(sender);
                        try {
                            callFormSocket(classes,privateMessage,"onPrivateMessage");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }else if(type.equals("GroupMessage")){
                        GroupSender sender = JSONObject.parseObject(messageJson.getString("sender"),GroupSender.class);
                        GroupMessage groupMessage = new GroupMessage();
                        groupMessage.setMessageId(messageId);
                        groupMessage.setMessage(context);
                        groupMessage.setMessageChain(messageChain);
                        groupMessage.setMessageType(type);
                        groupMessage.setSender(sender);
                        try {
                            callFormSocket(classes,groupMessage,"onGroupMessage");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    System.out.println("[websocket] 收到消息="+message);

                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("[websocket] 退出连接");
                }

                @Override
                public void onError(Exception ex) {
                    System.out.println("[websocket] 连接错误={}"+ex.getMessage());
                }

                private Long getMessageId(String messageChain){
                    JSONArray messages = JSON.parseArray(messageChain);
                    for(int i =0;i<messages.size();i++){
                        JSONObject message = messages.getJSONObject(i);
                        if(message.getString("type").equals("Source")){
                            return Long.parseLong(message.getString("id"));
                        }
                    }
                    return -1L;
                }

                private String getMessageContext(String messageChain){
                    JSONArray messages = JSON.parseArray(messageChain);
                    String context = "";
                    for(int i =0;i<messages.size();i++){
                        JSONObject message = messages.getJSONObject(i);
                        if(message.getString("type").equals("Plain")){
                            context += message.getString("text");
                        }
                    }
                    return context;
                }

                private void callFormSocket(Set<Class<?>> classes, Message message,String methodName) throws InstantiationException, IllegalAccessException, InvocationTargetException {
                    boolean flag = true;
                    for(Class<?> cls : classes){
                        if(!flag) return;
                        Method method = null;
                        try {
                            method = cls.getMethod(methodName,Mirai.class,GroupMessage.class);//获取类中所有的方法
                        }catch (NoSuchMethodException e){
                            continue;
                        }
                        Object obj = cls.newInstance();
                        flag = (boolean)  method.invoke(obj,mirai,message);
                     }
                }
            };
            webSocketClient.connect();
            return webSocketClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
