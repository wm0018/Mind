package com.wu.demo.entity.wx.req;

/**
 * 图片消息
 * @author Wu
 *
 */
public class TextReqMessage extends ReqBaseMessage {
	//消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
