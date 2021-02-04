package com.jh.mirai.http.plus.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.jh.mirai.http.plus.r6.Data;
import com.jh.mirai.http.plus.util.OkHttpClientUtil;
import okhttp3.Response;

public class R6Server {

    private  static String id_url = "https://r6stats.com/api/player-search/";

    private  static String data_url = "https://r6stats.com/api/stats/";

    public static String getUserId(String gameName) throws Exception {

        Response response = OkHttpClientUtil.getInstance().getData(id_url+gameName+"/pc");

        String jsonString = response.body().string();
        System.out.println(jsonString);
        JSONObject jsonMap;

        try{
            jsonMap = JSON.parseObject(jsonString);
        }catch (JSONException e){
            return null;
        }
        JSONArray array = jsonMap.getJSONArray("data");
        JSONObject json  = (JSONObject) array.get(0);
        System.out.println(json.getString("uplay_id"));
        return json.getString("uplay_id");
    }

    public static String getData(String uplayId)throws Exception {
        System.out.println("!---------------------------------");
        Response response = OkHttpClientUtil.getInstance().getData(data_url+uplayId);
        String jsonString = response.body().string();
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        System.out.println(jsonString);
        Data data = JSONObject.parseObject(jsonObject.getString("data"),Data.class);
        System.out.println(data);
        String fs = "["+ data.getProgression().getLevel()+"]"+data.getUsername()+"-刷包概率："+data.getProgression().getLootbox_probability()/100.0+"% 的战绩如下：" +
                "\n总计：" +
                "\nKD(击杀/死亡)\n"+data.getStats().get(0).getGeneral().getKd()+
                "("+data.getStats().get(0).getGeneral().getKills()+
                "/"+data.getStats().get(0).getGeneral().getDeaths()+")" +
                "\n近战/穿透/致盲："+data.getStats().get(0).getGeneral().getMelee_kills()+"/"+data.getStats().get(0).getGeneral().getPenetration_kills()+"/"+data.getStats().get(0).getGeneral().getBlind_kills()+
                "\n胜负比(胜/负)\n"+data.getStats().get(0).getGeneral().getWl()+
                "("+data.getStats().get(0).getGeneral().getWins()+
                "/"+data.getStats().get(0).getGeneral().getLosses()+")" +
                "\n休闲："+
                "\nKD(击杀/死亡)\n"+data.getStats().get(0).getQueue().getCasual().getKd()+
                "("+data.getStats().get(0).getQueue().getCasual().getKills()+
                "/"+data.getStats().get(0).getQueue().getCasual().getDeaths()+")"+
                "\n胜负比(胜/负)\n"+data.getStats().get(0).getQueue().getCasual().getWl()+
                "("+data.getStats().get(0).getQueue().getCasual().getWins()+
                "/"+data.getStats().get(0).getQueue().getCasual().getLosses()+")" +
                "\n排位："+
                "\nKD(击杀/死亡)\n"+data.getStats().get(0).getQueue().getRanked().getKd()+
                "("+data.getStats().get(0).getQueue().getRanked().getKills()+
                "/"+data.getStats().get(0).getQueue().getRanked().getDeaths()+")"+
                "\n胜负比(胜/负)\n"+data.getStats().get(0).getQueue().getRanked().getWl()+
                "("+data.getStats().get(0).getQueue().getRanked().getWins()+
                "/"+data.getStats().get(0).getQueue().getRanked().getLosses()+")";

        return fs;
    }
}
