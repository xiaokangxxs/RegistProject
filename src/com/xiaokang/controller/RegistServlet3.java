package com.xiaokang.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaokang.pojo.User;
import com.xiaokang.util.JsonpUtil;

/**
 * 
 * @Description: 注册控制器
 * @author 小康
 * @version V1.0.0 2019年11月23日 下午1:12:31
 */
@WebServlet("/regist-jsonp.action")
public class RegistServlet3 extends HttpServlet {
	private static final long serialVersionUID = 7777309695095289879L;

	public RegistServlet3() {
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		String contentType = req.getContentType();
//		String callback = req.getParameter("callback");
		// jsonpCallback:"xiaokang", 指定callback的函数名为xiaokang
		// jsonp:"jsonpCall", 指定Get请求中需要传送的指定参数key,默认的key是callback
		String callback = req.getParameter("jsonpCall");
		String username = req.getParameter("username");
		String pwd = req.getParameter("pwd");
		System.out.println("用户名>>>" + username + "密码>>>" + pwd + "Content-Type>>>" + contentType);
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter writer = resp.getWriter();
		User user = new User(666, username, pwd);

		String responseText = JsonpUtil.getResponseText(callback, user);
		System.out.println(responseText);
		writer.print(responseText);
		writer.flush();
		writer.close();
	}

}
