package com.wu.demo.entity.wx.req;

/**
 * 图片消息
 * @author Wu
 *
 */
public class ImageMessage extends ReqBaseMessage{
	//图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

}
