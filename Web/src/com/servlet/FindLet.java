package com.servlet;
 
import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.service.Service;
 
public class FindLet extends HttpServlet{
 
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
				String email = arr[1];
				String []arr1=new String(request.getParameter("password").getBytes("ISO-8859-1"),"UTF-8").split("/");
				String phone=arr1[0];
				String pwd=arr1[1];
				System.out.println(username + "/" + email+ "/" + phone+ "/" + pwd);
				
				//新建服务对象
				Service service = new Service();
					
				//验证处理
				boolean find = service.find(username,email,phone,pwd);
				if( find ){
					System.out.println("find success");
					//request.getSession().setAttribute("username", username);
				}else{
					System.out.println("find fail");
				}
					
				//返回信息到客户端
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html");
				PrintWriter out = response.getWriter();
				if(find){
					out.print("true");
				}else{
					out.print("false");
				}
				out.flush();
				out.close();
	}
 
}
