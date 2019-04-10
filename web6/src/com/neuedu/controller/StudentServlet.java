package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.neuedu.common.AdminOperationEnum;
import com.neuedu.common.ServerResponse;
import com.neuedu.common.StudentOperationEnum;
import com.neuedu.service.impl.AdminServiceImpl;
import com.neuedu.service.impl.StudentServiceImpl;
import com.nueedu.pojo.Student;




/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StudentServiceImpl stu=StudentServiceImpl.GetInstance();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Cookie[] cookies = request.getCookies();
		String username=null;
		String password=null;
		if(cookies!=null) {
		  for(int i=0;i<cookies.length;i++) {
			  Cookie cookie = cookies[i];
			  String cookie_name = cookie.getName();
			  String cookie_value = cookie.getValue();
			  if(cookie_name.equals("username")) {
				  username=cookie_value;
			  }
			  if(cookie_name.equals("password")) {
				  password=cookie_value;
			  }
		  }
		}
		if(username!=null&&password!=null) {
			AdminServiceImpl service = AdminServiceImpl.GetInstance();
			ServerResponse serverResponse = service.login(username, password);
			response.getWriter().write(serverResponse.obj2str());
		}
		PrintWriter pw = response.getWriter();
		String operation = request.getParameter("operation");
		if(operation==null) {
			//返回客户端，opertion参数必须传递->json
			//{"status":3,"msg":"opreation参数必须传递"}
			String responseText="{\"status\":3,\"msg\":\"opreation参数必须传递\"}";
			pw.write(responseText);
			pw.close();
		}
		try {
			int _operation=Integer.parseInt(operation);
			if(_operation==StudentOperationEnum.STUDENTADD.getOperation_type()) {//添加学生
				 stu.add(4,"小睿", "男", 23, "高级", "山西省晋中市", "16569056668",
							"324385849@qq.com",100);
			}else if(_operation==StudentOperationEnum.STUDENTCHECK.getOperation_type()) {
			    String responseText = ServerResponse.createServerResponseBySucc("获取成功", stu).obj2str();
				pw.write(responseText);
				pw.close();
			}else if(_operation==StudentOperationEnum.STUDENTSELECTBYID.getOperation_type()) {
				String id1 = request.getParameter("id");
				int id2 = Integer.parseInt(id1);
				ServerResponse<Student> serverResponse = stu.selectById(id2);
				String responseText = serverResponse.obj2str();
				pw.write(responseText);
				pw.close();
			}else if(_operation==StudentOperationEnum.STUDENTUPDATE.getOperation_type()) {
				
			}else if(_operation==StudentOperationEnum.STUDENTDELETE.getOperation_type()) {
				
			}
		}catch(NumberFormatException e) {
			//返回客户端，operation必须是客户端
			//{"status":4,"msg":"operation必须为数字"}
			String responseText="{\"status\":4,\"msg\":\"operation必须为数字\"}";
			pw.write(responseText);
			pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
