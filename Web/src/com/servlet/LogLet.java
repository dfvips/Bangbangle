package com.servlet;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.service.Service;
 
public class LogLet extends HttpServlet{
 
	private static final long serialVersionUID = 9036889586892331384L;
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//���ܿͻ�����Ϣ
		String username = request.getParameter("username");
		username = new String(username);
		String password = request.getParameter("password");
		password = new String(password);
		System.out.println(username + ":" + password);
		
		//�½��������
		Service service = new Service();
		
		//��֤����
		boolean log = service.login(username, password);
		if( log ){
			System.out.println("log success");
			//request.getSession().setAttribute("username", username);
		}else{
			System.out.println("log fail");
		}
		
		//������Ϣ���ͻ���
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if( log ){
			String usemsg=service.getusermsg();
			System.out.println(usemsg);
			out.print(usemsg);
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
