package com.redis.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONSerializer;
import redis.clients.jedis.Jedis;

public class SelectStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		@SuppressWarnings("resource")
		Jedis jedis= new Jedis("127.0.0.1", 6379);
		jedis.auth("yh345678");
		Set<String> set = jedis.smembers("stu");
		Iterator<String> it = set.iterator();
		List<JSONArray> list=new ArrayList<JSONArray>();
		String pagestr=request.getParameter("page");
		int page;
		Long length = jedis.scard("stu");
		if(pagestr!=null) {
			page=Integer.parseInt(pagestr);
			if(page<1) {
				page=1;
			}else if(length/10<page&&length%10==0) {
				page=(int) (length/10);
			}else if(length/10<page&&length%10!=0) {
				page=(int) (length/10)+1;
			}
		}else {
			page=1;
		}
		while(it.hasNext()){
			Object obj = it.next();
			JSONArray array = (JSONArray)JSONSerializer.toJSON(obj);
			for(int i=0;i<list.size()&&page*10>=i;i++) {
				if(list.get(i).get(4).toString().equals("")) {
					JSONArray o=array;
					array=list.get(i);
					list.set(i, o);
				}else if(!array.get(4).toString().equals("")&&list.get(i).getInt(4)<array.getInt(4)) {
					JSONArray o=array;
					array=list.get(i);
					list.set(i, o);
				}
			}
			list.add(array);
		}
		request.setAttribute("list", list);
		request.getRequestDispatcher("/selectStudent.jsp?page="+page).forward(request, response);
	}

}
