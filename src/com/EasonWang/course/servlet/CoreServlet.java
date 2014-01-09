package com.EasonWang.course.servlet;


import java.io.IOException;
import java.io.PrintWriter;


import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EasonWang.course.service.*;
import com.EasonWang.course.util.SignUtil;

/**
 * sevlet请求类
 * @author EasonWang
 * @date 2013-10-30
 */
public class CoreServlet extends HttpServlet {

	/**
	 * 确认请求来自微信
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// 微信加密签名
		String signature = req.getParameter("signature");
		// 时间戳
		String timestamp = req.getParameter("timestamp");
		// 随机数
		String nonce = req.getParameter("nonce");
		// 随机字符串
		String echostr = req.getParameter("echostr");
		
		resp.setContentType("text/html;charset=utf-8");
		
		PrintWriter out = resp.getWriter();
		out.write("这是第一个serlet????");
		/*
		PrintWriter out = resp.getWriter();
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			out.write(echostr);
		}
		*/
		out.close();
		out = null;
	}
	
	/** 
     * 处理微信服务器发来的消息 
     */  
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
    	 // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
    	
    	request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
        // 调用核心业务类接收消息、处理消息  
        String respMessage = CoreService.processRequest(request);  
        
        // 响应消息  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close();  
    }  
}
