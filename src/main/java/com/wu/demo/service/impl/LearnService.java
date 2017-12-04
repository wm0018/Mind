package com.wu.demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.wu.demo.dao.ILearnDao;
import com.wu.demo.entity.LearnResource;
import com.wu.demo.service.ILearnService;

@Service
public class LearnService implements ILearnService {

	
	@Resource
	private ILearnDao learnDao;
	@Override
	public List<LearnResource> queryLearnResouceList(Map<String, Object> para) {
		PageHelper.startPage(Integer.parseInt(para.get("page").toString()), Integer.parseInt(para.get("rows").toString()));
		return learnDao.getList(para);
	}

}
