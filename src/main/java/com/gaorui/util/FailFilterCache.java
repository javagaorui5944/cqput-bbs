package com.gaorui.util;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 凯 on 2016/10/21.
 */
public class FailFilterCache {

    private static final int LOGIN_ERROR_NUM = 5;

    private FailFilterCache(){};

    private static FailFilterCache cache = null;

    private Map<String,CodeSequence> map = null;

    /*
    * 定时清理登录错误用户列表
     *@param param
     */
    public void clear(Long param){
        CodeSequence cs = null;
        Long date = new Date().getTime();
        Long interval = (param == null)?1000*60*5:param;
        Map<String,CodeSequence> map = this.getMap();
        Iterator<Map.Entry<String,CodeSequence>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,CodeSequence> entry = iterator.next();
            cs = entry.getValue();
            if(cs.getTimeSequence()<(date-interval)){
                iterator.remove();
                continue;
            }
        }
            System.out.println("定时清理登录失败用户列表，当前列表大小:"+map.size());
            iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,CodeSequence> entry = iterator.next();
            System.out.println("当前登录出错用户："+entry.getKey()+"当前登录出错次数"+entry.getValue().getTimeSequence());
        }
    }

    /*
    * 获取FailFilterCache实例
     *@return FailFilterCache
     */
    public static FailFilterCache getInstance(){
        if(cache == null){
            cache = new FailFilterCache();
        }
        return cache;
    }

    /*
    * 获取map对象
     *@return Map
     */
    public Map<String,CodeSequence> getMap(){
           if(this.map == null){
                this.map =  new HashMap<String,CodeSequence>();
           }
        return this.map;
    }

    /*
    * 增加错误登录次数
    * @param userId
    */
    public void setErrorNum(String userId){
        Map<String,CodeSequence> map = this.getMap();
        CodeSequence cs = map.get(userId);
        if(cs == null){
            cs = new CodeSequence(new Date().getTime(),1);
        }else{
            cs.setTimeSequence(new Date().getTime());
            cs.setErrorNum(cs.getErrorNum()+1);
        }
        map.put(userId,cs);
    }

    /**
     * 清空错误登录记录
     * @param userId
     */
    public void resetErrorNum(String userId){
        Map<String,CodeSequence> map = this.getMap();
        Iterator<Map.Entry<String,CodeSequence>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String,CodeSequence> entry = iterator.next();
            if(entry.getKey().equals(userId)){
                iterator.remove();
                break;
            }
        }
    }

    /**
     * 获取当前登录用户名登录出错次数
     * @param userId
     */
    public Integer getErrorNum(String userId){
          if(CommonUtil.isNullOrBlank(userId)) return null;
          Map<String,CodeSequence> map = this.getMap();
          CodeSequence cs = map.get(userId);
          if(cs!=null){
              return cs.getErrorNum();
          }
        return 0;
    }

    /**
     * 判读当前用户是否登录次数过多
     * @param userId
     * @return String
     */
    public String getLoginErrorInfo(String userId){
        if(CommonUtil.isNullOrBlank(userId)) return null;
        Integer errorNum = getErrorNum(userId);
        if(errorNum.intValue()>FailFilterCache.LOGIN_ERROR_NUM){
            return "登录失败次数过多，请稍后重试！";
        }else{
            return null;
        }
    }

    public class CodeSequence{

        private Long timeSequence;

        private Integer errorNum;

        public CodeSequence(){};

        public CodeSequence(Long timeSequence,Integer errorNum){
                this.timeSequence = timeSequence;
                this.errorNum = errorNum;
        }

        public Integer getErrorNum() {
            return errorNum;
        }

        public void setErrorNum(Integer errorNum) {
            this.errorNum = errorNum;
        }

        public Long getTimeSequence() {
            return timeSequence;
        }

        public void setTimeSequence(Long timeSequence) {
            this.timeSequence = timeSequence;
        }
    }
}
