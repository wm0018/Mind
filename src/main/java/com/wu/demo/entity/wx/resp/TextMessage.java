package com.wu.demo.entity.wx.resp;

/**
 * 文本消息
 * @author Wu
 *
 */
public class TextMessage extends RespBaseMessage{
	//回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
