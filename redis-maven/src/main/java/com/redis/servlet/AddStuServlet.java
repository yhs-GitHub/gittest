package com.redis.servlet;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import redis.clients.jedis.Jedis;

public class AddStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		System.out.println(1);
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String birth = request.getParameter("birth");
		String mark = request.getParameter("mark");
		String grade =request.getParameter("grade");
		@SuppressWarnings("resource")
		Jedis jedis= new Jedis("127.0.0.1", 6379);
		jedis.auth("yh345678");
		List<String> list=new ArrayList<String>();
		list.add(id);
		list.add(name);
		list.add(birth);
		list.add(mark);
		list.add(grade);
		JSONArray array = (JSONArray)JSONSerializer.toJSON(list);
		String stu = array.toString();
		jedis.sadd("stu", stu);
		request.getRequestDispatcher("SelectStuServlet").forward(request, response);
	}
}
