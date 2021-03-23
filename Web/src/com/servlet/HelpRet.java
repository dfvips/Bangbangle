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
		// TODO 自动生成的方法存根
	}
 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		//接受客户端信息
				String problem=new String(request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8");
				String []arr=new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8").split("/");
				String name=arr[0];
				String phone=arr[1];
				System.out.println(problem + "/" +name+  "/" + phone);
				
				//新建服务对象
				Service service = new Service();
					
				//验证处理
				boolean help = service.help(problem, name,phone);
				if(help){
					System.out.println("help  success");
					//request.getSession().setAttribute("username", username);
				}else{
					System.out.println("help  fail");
				}
					
				//返回信息到客户端
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
