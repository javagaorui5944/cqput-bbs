package com.gaorui.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gaorui on 16/11/18.
 */
public class patterntest {
    public static  void main(String args[]){
        List<String> user_LoignName = new ArrayList<String>();
        Pattern pt = Pattern.compile("@\\w+([,\\.\\:\\;\\?\\!\\'\\\"]|\\s|，|。|？|；|！|‘|’|“|”)");
        Matcher mt = pt.matcher("@biezhi  @biezhi  @biezhi  ");
        while (mt.find()) {
            user_LoignName.add(mt.group().replace("@",""));
        }
        System.out.println();
    }
}
