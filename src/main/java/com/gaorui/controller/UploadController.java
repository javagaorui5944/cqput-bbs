package com.gaorui.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaorui.util.CommonUtil;
import com.gaorui.util.PropertiesUtils;
import com.gaorui.util.SftpUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

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


    /**
     * 图片以及其它类型文件上传
     * @param
     * @return
     */
    @RequestMapping("/Picture")
    @ResponseBody
    public JSONObject Picture(HttpServletRequest request) {
        SimpleDateFormat myFmt2 = new SimpleDateFormat("yyyyMMdd");// 等价于now.toLocaleString()
        Date date = new Date();
        // 时间
        String uploadDate = myFmt2.format(date);

        String result = null;// 上传后返回情况说明
        String path = null;// 上传图片路径
        // //创建一个通用的多部分解析器
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(request
                .getSession().getServletContext());
        // //判断 request 是否有文件上传,即多部分请求
        if (cmr.isMultipart(request)) {
            // 转换成多部分request
            MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) request;
            // //取得request中的所有文件名
            Iterator<String> iter = mhsr.getFileNames();
            while (iter.hasNext()) {
                // //取得上传文件
                MultipartFile file = mhsr.getFile((String) iter.next());

                // //取得当前上传文件的文件名称
                String filename = file.getOriginalFilename();
                // 获得文件后缀
                String fileSuffixName = filename.substring(filename
                        .indexOf("."));
                /**
                 * 上传文件大小,类型判断
                 */
                if (file.getSize() > 1048576) {
                    result = "上传失败：上传文件大小大于1M";

                    return CommonUtil.constructResponse(0, result, null);

                } else if (!fileSuffixName.equals(".jpg")
                        && !fileSuffixName.equals(".png")
                        && !fileSuffixName.equals(".gif")) {
                    result = "上传失败：上传文件类型不正确";

                    return CommonUtil.constructResponse(0, result, null);

                }

                // 生成的GUID为一串32位字符组成的128位数据上传文件重命名filename1
                CommonUtil cu = new CommonUtil();
                String UUID = cu.GUID();

                String filename1 = UUID + fileSuffixName;
                // 验证当前操作系统，构建上传路径
                String os = System.getProperty("os.name").toLowerCase();
                if (os.indexOf("win") >= 0)
                    path = PropertiesUtils.getProp("path.win") + "/"
                             + "/" + uploadDate;
                else
                    path = PropertiesUtils.getProp("path.linux") + "/"
                             + "/" + uploadDate;

                // 定义上传路径
                // path =
                // "http://103.249.252.139:9001/usr/sendfoodpic/"+filename1;
                // String path2 =
                // path = "/static/image/icon/"+filename1;


                File file2 = new File(path);
                if (!file2.exists()) {
                    file2.mkdirs();
                }

                File file3 = new File(path + "/" + filename1);
                try {
                    // transfer方法是MultipartFile包中提供的方法，直接可以写入文件到指定目录
                    file.transferTo(file3);

                    // 复制Web服务器文件到文件服务器
                    boolean status = SftpUtils.uploadFile(path + "/"
                            + filename1);
                    if (status) {
                        // 存入数据库

                        result = "上传成功";
                        // 删除WEB服务器文件
                        file3.delete();
                        // TODO url 需要讨论
                        return CommonUtil.constructResponse(1, result,
                                  "/" + uploadDate + "/"
                                        + filename1);
                    }

                } catch (Exception e) {
                    result = e.getMessage();

                    e.printStackTrace();
                    return CommonUtil.constructResponse(0, result, null);
                }

            }

        }
        return CommonUtil.constructResponse(0, null, null);
    }
}
