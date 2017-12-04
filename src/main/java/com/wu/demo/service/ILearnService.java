package com.wu.demo.service;

import java.util.List;
import java.util.Map;

import com.wu.demo.entity.LearnResource;

public interface ILearnService {

	public List<LearnResource> queryLearnResouceList(Map<String, Object> para);
}
