package com.wu.demo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wu.demo.common.ConfigBeans;
import com.wu.demo.entity.wx.resp.TextMessage;
import com.wu.demo.util.HelpUtil;
import com.wu.demo.util.MessageUtil;

import net.sf.json.JSONObject;



@RestController
@RequestMapping("/")
@EnableAutoConfiguration
public class CoreController {
	
	private static Logger log = LoggerFactory.getLogger(CoreController.class);
	@Autowired
	ConfigBeans configBeans;
	@RequestMapping(method=RequestMethod.GET)
	public String core(String signature, String timestamp, String nonce, String echostr) {
		if(HelpUtil.checkSignature(signature, timestamp, nonce,configBeans.getToken())){
			return echostr;
		}
		return "error";
	}
	@RequestMapping(method=RequestMethod.POST)
	public String service(HttpServletRequest req) {
		Map<String,String> requestMap;
		String respContent="";
		String respMessage=null;
		try {
			requestMap = MessageUtil.parseXml(req);
			log.debug(requestMap.toString());
			String msgType = requestMap.get("MsgType");
			String fromUserName = requestMap.get("FromUserName");
			String toUserName = requestMap.get("ToUserName");
			
			// 回复文本消息  
			TextMessage textMessage = new TextMessage();  
			textMessage.setToUserName(fromUserName);  
			textMessage.setFromUserName(toUserName);  
			textMessage.setCreateTime(new Date().getTime());  
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
			textMessage.setFuncFlag(0);
			
			if(msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
				//事件类型
				String eventType = requestMap.get("Event");
				// 订阅  
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {  
                    respContent = "你好，欢迎关注小豚当家！";
                }else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {  
                	// 取消订阅
                	// 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                	respContent = "用户已退订!";
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){  
					
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_VIEW)){
                	respContent="跳转自定义View";
                }else if(eventType.equals(MessageUtil.EVENT_TYPE_SCAN)){
                	
                }else {
					return respMessage;
				}
                log.debug(respContent);
                textMessage.setContent(respContent);  
                respMessage = MessageUtil.textMessageToXml(textMessage);
                log.debug(respMessage);
			}else if(msgType.equals(MessageUtil.RESP_MESSAGE_TYPE_TEXT)){
				log.debug("text消息");
				List<NameValuePair> list = new ArrayList<>();
				list.add(new BasicNameValuePair("key", configBeans.getRobotkey()));
				list.add(new BasicNameValuePair("info", requestMap.get("Content")));
				list.add(new BasicNameValuePair("userid", fromUserName));
				String json = HelpUtil.httpPost(configBeans.getRoboturl(), list);
				textMessage.setContent(json);  
	            respMessage = MessageUtil.textMessageToXml(textMessage);
			}else {
				
			}
		} catch (Exception e) {
			log.error(ExceptionUtils.getStackTrace(e));
		}
		return respMessage;
	}
}
