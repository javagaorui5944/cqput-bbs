package com.gaorui.push;


import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

/**
 * Created by gaorui on 16/10/19.
 */
@ServerEndpoint("/WebPush")
public class WebPush {

    //static Session session1 = null;
    //wesoket各环境兼容问题,利用容器解决掉session问题,暂不考虑并发
    static HashMap<String,Session> Sessions = new HashMap<String,Session>();

    @OnMessage
    public void hello(String message) {


            String[] res = message.split(",");
            JSONObject jsonObject = new JSONObject();
            //jsonObject.put("textID", res[0]);
            //jsonObject.put("Username", res[1]);

            Session openSession =null;
            try {

                // 把消息发送给所有连接的会话
                Set<String> keys=Sessions.keySet();
                Iterator<String> temp =keys.iterator();
                while(temp.hasNext()){
                    String i= temp.next();
                    openSession = Sessions.get(i);
                    openSession.getBasicRemote()
                            .sendText(jsonObject.toString());
                }

            } catch (Exception e) {

                e.printStackTrace();
            }


    }

    @OnOpen
    public void myOnOpen(Session session ) {

        Sessions.put(session.getId(), session);
    }

    @OnClose
    public void myOnClose(CloseReason reason, Session session ) {

        Sessions.remove(session.getId());
    }

}
