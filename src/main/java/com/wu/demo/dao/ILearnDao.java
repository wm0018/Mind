package com.wu.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.wu.demo.entity.LearnResource;

@Mapper
public interface ILearnDao {

	public List<LearnResource> getList(Map<String, Object> para);
}
