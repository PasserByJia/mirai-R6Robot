package com.jh.mirai.http.plus.plug;


import com.jh.mirai.http.plus.annoation.Receive;
import com.jh.mirai.http.plus.domain.Group;
import com.jh.mirai.http.plus.domain.GroupMessage;
import com.jh.mirai.http.plus.service.Mirai;
import com.jh.mirai.http.plus.service.MiraiPlug;
import com.jh.mirai.http.plus.domain.Message;
import com.jh.mirai.http.plus.service.R6Server;

import java.io.*;

@Receive
public class DemoPlug extends MiraiPlug {

    String[] me ={"#绑定账号 ID","#R6战绩 ID","#我的战绩"};
    String[] menu ={"#绑定账号","#R6战绩","#我的战绩"};

    @Override
    public boolean onPrivateMessage(Mirai mirai, Message message) {

        return true;
    }

    @Override
    public boolean onGroupMessage(Mirai mirai, GroupMessage message) throws Exception {
        String msg = message.getMessage();
        long groupId = message.getGroupId();
        long userId = message.getSenderId();
        if (msg.contains("#")) {
            String fs = r6(msg,userId);
            // 回复内容为 at发送者 + hi

            // 调用API发送消息
            mirai.sendGroupMsg(groupId,fs);

            // 不执行下一个插件
            return false;
        }
        return true;
    }

    @Override
    public boolean onTempMessage(Mirai mirai, Message message) {
        return true;
    }

    private String r6(String msg,Long userId) throws Exception {
        String  re = null;
        if(msg.equals("#菜单")){
            String fs;
            fs = String.format("1.绑定游戏ID:" +
                    "%s,\n2.查询自己战绩:" +
                    "%s,\n3.查询他人战绩:" +
                    "%s",me[0], me[2], me[1]);
            re =fs;
        }else if(msg.equals(menu[2])){//我的战绩
            Reader r;
            try {

                r=new FileReader("./"+userId+".txt");//.....处写文zd件路径回

            }catch (FileNotFoundException e){

                return "您还未绑定账号";

            }

            BufferedReader br=new BufferedReader(r);
            String s = br.readLine();
            r.close();
            br.close();
            re = R6Server.getData(s);
        }else {

            int index = msg.indexOf(" ");
            String code = null;

            try {

                code = msg.substring(0,index);

            }catch (StringIndexOutOfBoundsException e){

                return "哈麻皮，没这个命令！";

            }

            if(code.equals(menu[0])){//绑定ID

                String gameName = msg.substring(index).trim();

                String uplay_id = R6Server.getUserId(gameName);

                if(uplay_id==null) {

                    return "未找到账号";

                }

                File file =new File("./"+userId+".txt");

                if(!file.exists()){

                    file.createNewFile();

                }
                FileWriter fileWritter = new FileWriter(file.getName());
                BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
                bufferWritter.write(uplay_id);
                bufferWritter.close();
                re = "绑定成功";
            }else if (code.equals(menu[1])){//查询战绩

                String gameName = msg.substring(index).trim();

                String uplay_id = R6Server.getUserId(gameName);

                re = R6Server.getData(uplay_id);

            }else {
                re = "哈麻皮，没这个命令！";
            }
        }
        return re;
    }
}
