package com.gaorui.service;

import java.util.List;

/**
 * Created by gaorui on 16/10/19.
 */
public interface IShowNotice {
    int AddNoticeComment(int topicId, int uId);
    int ADDNoticeAt(int topicId,List<Integer> uIds,int uId);
}
