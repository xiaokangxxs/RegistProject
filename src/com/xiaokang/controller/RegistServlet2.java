package com.xiaokang.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.xiaokang.pojo.User;

/**
 * 
 * @Description: 注册控制器
 * @author 小康
 * @version V1.0.0 2019年11月23日 下午1:12:31
 */
@WebServlet("/regist-json.action")
public class RegistServlet2 extends HttpServlet {
	private static final long serialVersionUID = 3851523655175255238L;

	public RegistServlet2() {
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 设置请求编码格式
		req.setCharacterEncoding("utf-8");
		// 从输入流中拿数据
		// 1.获得输入流
		ServletInputStream is = req.getInputStream();
		// 2.解析输入流
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		StringBuffer sb = new StringBuffer();
		String temp;
		while ((temp = br.readLine()) != null) {
			sb.append(temp);
		}
		if (br != null) {
			br.close();
		}
		if (isr != null) {
			isr.close();
		}
		if (is != null) {
			is.close();
		}
		// 3.处理数据
		User reqUser = JSON.parseObject(sb.toString(), User.class);

		System.out.println("用户名>>>" + reqUser.getUsername() + "密码>>>" + reqUser.getPassword());

		// 设置响应编码格式
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter writer = resp.getWriter();
		User user = new User(reqUser.getId(), reqUser.getUsername(), reqUser.getPassword());

		String jsonString = JSON.toJSONString(user);

		writer.print(jsonString);
		writer.flush();
		writer.close();
	}

}
