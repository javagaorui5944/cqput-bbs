package com.gaorui.dao;

import com.gaorui.bean.NodeBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeDao {
	 List<NodeBean> ShowNode();
}
