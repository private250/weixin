package com.EasonWang.course.message.response;

/**
 * 响应消息--文本消息
 * @author EasonWang
 * @date 2013年10月31日
 */
public class TextMessage extends BaseMessage{
	 // 回复的消息内容  
    private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	} 
    
    
}
