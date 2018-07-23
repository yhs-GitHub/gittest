package com.redis.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import redis.clients.jedis.Jedis;

public class ChangeStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		Jedis jedis= new Jedis("127.0.0.1", 6379);
		jedis.auth("yh345678");
		DelStudentServlet del = new DelStudentServlet();
		Long num = del.delStu(id,jedis);
		if(num!=null) {
			AddStuServlet add = new AddStuServlet();
			add.doPost(request, response);
		}else {
			request.getRequestDispatcher("SelectStuServlet").forward(request, response);
		}
	}

}
