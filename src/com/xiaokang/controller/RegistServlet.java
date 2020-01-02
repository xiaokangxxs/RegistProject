package com.xiaokang.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;

import com.alibaba.fastjson.JSON;
import com.xiaokang.mappers.UserMapper;
import com.xiaokang.pojo.User;
import com.xiaokang.util.SqlSessionFactoryUtil;

/**
 * 
 * @Description: 注册控制器
 * @author 小康
 * @version V1.0.0 2019年11月23日 下午1:12:31
 */
@WebServlet("/regist.action")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = -4026154046105204192L;

	public RegistServlet() {
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String username = req.getParameter("username");
		String pwd = req.getParameter("pwd");
		System.out.println("用户名>>>" + username + "密码>>>" + pwd);
		resp.setContentType("text/html;charset=utf-8");
		resp.setCharacterEncoding("utf-8");
		PrintWriter writer = resp.getWriter();
		User user = new User(username, pwd);

		if ((username != null && StringUtils.isEmpty(username)) || (pwd != null && StringUtils.isEmpty(pwd))) {
			writer.print("{\"status\":\"failed\",\"msg\":\"账号或密码不能为空！\"}");
		} else {
//			String jsonString = "{\"name\":" + username + ",\"pwd\":" + pwd;
			String jsonString = JSON.toJSONString(user);
			System.out.println(jsonString);
			// 将数据通过Mybatis保存到数据库
			SqlSession sqlSession = SqlSessionFactoryUtil.openSqlSession();
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			int row = userMapper.addUser(user);
			// 增删改一定不要忘记提交
			sqlSession.commit();
			if (row > 0) {
				req.getRequestDispatcher("main.html").forward(req, resp);
				String jsonString2 = JSON.toJSONString(new User("result", "success"));
//				jsonString += ",\"result\":\"success\"}";
				System.out.println(jsonString2);
			}
			writer.print(jsonString);
			writer.flush();
			writer.close();
		}
	}

}
