package com.EasonWang.course.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.EasonWang.course.message.response.Article;
import com.EasonWang.course.message.response.NewsMessage;
import com.EasonWang.course.message.response.TextMessage;
import com.EasonWang.course.util.MessageUtil;

/**
 * 核心服务类
 * @author EasonWang
 * @date 2013年10月31日
 */
public class CoreService {
	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			
			
			// 回复文本消息(默认消息回复方式)
			TextMessage textMessage = new TextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);
			respContent = "感谢您订阅[王子帅同学]个人官方微信公众账号，立即回复以下【】中代码开启您的App新旅程！\n------\n【MN】查看美女图文\n 【QC】查看汽车图文 \n 【Lx】与我取得联系";
							
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				
				// 获取文本消息内容
				String msgContent = requestMap.get("Content");
				
				// 回复文本消息
				if("lx".equalsIgnoreCase(msgContent)){
					respContent = "感谢您与我们取得联系。\n-----------\n 请将您的意见或建议发送到下面的邮箱中，我们会第一时间进行处理\n easonwzs@126.com";
					textMessage.setContent(respContent);
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
				// 回复图文消息
				else if("MN".equalsIgnoreCase(msgContent)){
					
					NewsMessage newsMessage = new NewsMessage();
					newsMessage.setToUserName(fromUserName);
					newsMessage.setFromUserName(toUserName);
					newsMessage.setCreateTime(new Date().getTime());
					newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					newsMessage.setFuncFlag(1);
					
					// 向图文消息类添加item					
					List<Article> items = new ArrayList<Article>();
					Article article1 = new Article("长发美女", "测试描述", "http://d.hiphotos.baidu.com/image/w%3D2048/sign=5befa00b0bf79052ef1f403e38cbd6ca/c75c10385343fbf2c95a753bb27eca8065388fb0.jpg", "http://image.baidu.com/detail/index?from=1&pn=45&picture_id=9276115835&album_id=380323480&app_id=578130&sortlog=0&user_id=149095500&column=美女&tag=长发&sort=0#pn45&-1&id9276115835");
					Article article2 = new Article("SD品牌", "测试描述", "http://www.ds.com.cn/media/imgs/cars/newds5/wallpaper/642x362/ds5_wallpaper5_thu_b.jpg", "http://www.ds.com.cn");
					
					items.add(article1);
					items.add(article2);
					
					newsMessage.setArticleCount(items.size());
					newsMessage.setArticles(items);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}else
				{
					// 默认回复
					textMessage.setContent(respContent);
					respMessage = MessageUtil.textMessageToXml(textMessage);
				}
			}
			// 图片消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
				respContent = "已收到您发送的照片!";
				textMessage.setContent(respContent);
				respMessage = MessageUtil.textMessageToXml(textMessage);
			}
			// 地理位置消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
				respContent = "您发送的是音频消息！";
			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = "谢谢您的关注！";
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// TODO 自定义菜单权没有开放，暂不处理该类消息
				}
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
}
