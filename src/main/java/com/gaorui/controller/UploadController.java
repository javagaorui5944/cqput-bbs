package com.gaorui.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaorui.util.CommonUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

/**
 * Created by gaorui on 16/10/17.
 */
@Controller
@RequestMapping(value="Upload")
public class UploadController {


    /**
     * topic语音录制上传
     * @param request
     * @param mp3Name
     * @param file
     * @return
     */
    @RequestMapping(value="MpThree")
    @ResponseBody
    public JSONObject ShowUser(HttpServletRequest request,String mp3Name, Object file){


        InputStream inputStream =null;
        try {
            inputStream = request.getInputStream();
            FileOutputStream fos = new FileOutputStream("/Users/gaorui/mp3/memp3.mp3");//死路径,后期更改
            byte[] b = new byte[1024];
            while((inputStream.read(b)) != -1){
                fos.write(b);
            }
            inputStream.close();
            fos.close();
        } catch (IOException e) {

            e.printStackTrace();
            return  CommonUtil.constructResponse(0,"录音mp3上传失败",e.toString());
        }


        return  CommonUtil.constructResponse(1,"录音mp3上传成功",null);
    }
}
