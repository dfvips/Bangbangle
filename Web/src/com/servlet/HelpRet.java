package com.servlet;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.service.Service;
 
public class HelpRet extends HttpServlet{
 
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO �Զ����ɵķ������
	}
 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO �Զ����ɵķ������
		//���ܿͻ�����Ϣ
				String problem=new String(request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");
				String []arr=new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8").split("/");
				String name=arr[0];
				String phone=arr[1];
				System.out.println(problem + "/" +name+  "/" + phone);
				
				//�½��������
				Service service = new Service();
					
				//��֤����
				boolean help = service.help(problem, name,phone);
				if(help){
					System.out.println("help  success");
					//request.getSession().setAttribute("username", username);
				}else{
					System.out.println("help  fail");
				}
					
				//������Ϣ���ͻ���
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				if(help){
					out.print("true");
				}else{
					out.print("false");
				}
				out.flush();
				out.close();
	}
 
}
