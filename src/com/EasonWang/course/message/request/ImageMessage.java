package com.EasonWang.course.message.request;

/**
 * 消息请求--图片消息
 * @author EasonWang
 * @date 2013年10月31日
 */
public class ImageMessage extends BaseMessage {
	//图片链接
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	
}
