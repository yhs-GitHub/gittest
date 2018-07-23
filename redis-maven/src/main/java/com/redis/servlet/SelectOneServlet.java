package com.redis.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import redis.clients.jedis.Jedis;

public class SelectOneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		@SuppressWarnings("resource")
		Jedis jedis= new Jedis("127.0.0.1", 6379);
		jedis.auth("yh345678");
		Set<String> set = jedis.smembers("stu");
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			Object obj = it.next();
			JSONArray array = (JSONArray)JSONSerializer.toJSON(obj);
			if(array.get(0).equals(id)) {
				String name =array.getString(1);
				String birth = array.getString(2);
				String mark = array.getString(3);
				String grade =array.getString(4);
				request.setAttribute("name", name);
				request.setAttribute("birth", birth);
				request.setAttribute("mark", mark);
				request.setAttribute("grade", grade);
			}
		}
		request.getRequestDispatcher("/changeStudent.jsp").forward(request, response);
	}

}
