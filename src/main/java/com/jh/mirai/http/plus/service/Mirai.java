package com.jh.mirai.http.plus.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jh.mirai.http.plus.domain.Plain;
import com.jh.mirai.http.plus.util.OkHttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Mirai {

    OkHttpClientUtil client = OkHttpClientUtil.getInstance();

    @Value("${websocket}")
    private String websocketUrl;

    @Value("${qq}")
    private String qq;

    public Long sendGroupMsg(Long groupId,String context) throws IOException {
        Plain plain = new Plain(context);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionKey",System.getProperty("sessionKey"));
        jsonObject.put("target",groupId);
        JSONArray array = new JSONArray();
        array.add(plain);
        jsonObject.put("messageChain",array);
        System.out.println(jsonObject.toJSONString());
        JSONObject result =  client.postJsonObject("http://"+websocketUrl+"/sendGroupMessage",jsonObject.toJSONString());
        System.out.println(result);
        return 1L;
    }

    public void getSession(String authKey)throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("authKey",authKey);
        System.out.println("http://"+websocketUrl+"/auth");
        JSONObject result =  client.postJsonObject("http://"+websocketUrl+"/auth",jsonObject.toJSONString());
        int code = result.getInteger("code");
        if(code ==0){
            System.setProperty("sessionKey",result.getString("session"));
        }else {
            throw  new Exception(result.getString("msg"));
        }
    }

    public int auth(String authKey) throws Exception {
        getSession(authKey);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sessionKey",System.getProperty("sessionKey"));
        jsonObject.put("qq",qq);
        JSONObject result =  client.postJsonObject("http://"+websocketUrl+"/verify",jsonObject.toJSONString());
        int code = result.getInteger("code");
        if (code ==0){
            return code;
        }else {
            throw  new Exception(result.getString("msg"));
        }
    }
}
