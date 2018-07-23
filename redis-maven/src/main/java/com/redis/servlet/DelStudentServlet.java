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


public class DelStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		Jedis jedis= new Jedis("127.0.0.1", 6379);
		jedis.auth("yh345678");
		delStu(id,jedis);
		request.getRequestDispatcher("SelectStuServlet").forward(request, response);
	}
	Long delStu(String id,Jedis jedis) {
		Set<String> set = jedis.smembers("stu");
		Iterator<String> it = set.iterator();
		while(it.hasNext()){
			Object obj = it.next();
			JSONArray array = (JSONArray)JSONSerializer.toJSON(obj);
			if(array.get(0).equals(id)) {
				Long num = jedis.srem("stu", array.toString());
				System.out.println(num);
				return num;
			}
		}
		return null;
	}
}
