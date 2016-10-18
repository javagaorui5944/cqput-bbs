package com.gaorui.service.impl;

import com.gaorui.bean.NodeBean;
import com.gaorui.dao.NodeDao;
import com.gaorui.service.IShowNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplShowNode implements IShowNode {
	@Autowired  
	private NodeDao nodeDao;
	
	@Override
	public List<NodeBean> ShowNode() {
		// TODO Auto-generated method stub
		return nodeDao.ShowNode();
	}


}
