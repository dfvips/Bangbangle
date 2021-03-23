package com.servlet;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.service.Service;
 
public class RegLet extends HttpServlet{
 
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
	}
 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		//接受客户端信息
				String []arr=new String(request.getParameter("username").getBytes("ISO-8859-1"),"UTF-8").split("/");
				String username = arr[0];
				String password = arr[1];
				String []arr1=new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8").split("/");
				String email=arr1[0];
				String phone=arr1[1];
				System.out.println(username + "/" + password+ "/" + email+ "/" + phone);
				
				//新建服务对象
				Service service = new Service();
					
				//验证处理
				boolean reg = service.register(username, password,email,phone);
				if( reg ){
					System.out.println("reg success");
					//request.getSession().setAttribute("username", username);
				}else{
					System.out.println("reg fail");
				}
					
				//返回信息到客户端
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				if(reg){
					out.print("true");
				}else{
					out.print("false");
				}
				out.flush();
				out.close();
	}
 
}
