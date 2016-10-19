package com.gaorui.service.impl;

import com.gaorui.dao.NoticeDao;
import com.gaorui.dao.UserDao;
import com.gaorui.service.IShowNotice;
import com.gaorui.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

/**
 * Created by gaorui on 16/10/19.
 */
@Service
public class ImplShowNotice implements IShowNotice {
    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private UserDao userDao;

    @Override
    public int AddNoticeComment(int topicId, int uId) {
        int to_UId =  userDao.GetUserIDByTId(topicId);
        Long date =null;
        try {
             date =CommonUtil.getSystemTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int res = noticeDao.AddNoticeComment(topicId,uId,to_UId,date);
        return res;
    }
}
