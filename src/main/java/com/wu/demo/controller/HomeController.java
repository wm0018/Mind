package com.wu.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.wu.demo.entity.LearnResource;
import com.wu.demo.service.ILearnService;

@Controller
@RequestMapping("/home")
@EnableAutoConfiguration
public class HomeController {

	private Logger log = LoggerFactory.getLogger(HomeController.class);
	
	@Resource
	private ILearnService learnService;
	
	@RequestMapping("/")
	public ModelAndView jump(String page,String rows,String author,String title) {
        Map<String, Object> para = new HashMap<>();
        para.put("page", page);
        para.put("rows", rows);
        para.put("author", author);
        para.put("title", title);
        List<LearnResource> learnList = learnService.queryLearnResouceList(para);
        ModelAndView model = new ModelAndView("/test");
        model.addObject("learnList",learnList);
        log.info("共{}条记录", learnList.size());
		return model;
	}
}
