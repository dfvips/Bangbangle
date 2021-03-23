package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.service.Service;
 
public class AlterLet extends HttpServlet{
 
	private static final long serialVersionUID = 9036889586892331384L;
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���ܿͻ�����Ϣ
		String userid = request.getParameter("username");
		userid = new String(userid);
		String password = request.getParameter("password");
		password = new String(password);
		System.out.println(userid+":"+password);
		
		//�½��������
		Service service = new Service();
		
		//��֤����
		boolean alt = service.alter(userid,password);
		if( alt ){
			System.out.println("alt success");
			//request.getSession().setAttribute("username", username);
		}else{
			System.out.println("alt fail");
		}
		
		//������Ϣ���ͻ���
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if( alt ){
			out.print("true");
		}else{
			out.print("false");
		}
		out.flush();
		out.close();
	}
 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO �Զ����ɵķ������
 
	}
 
}
