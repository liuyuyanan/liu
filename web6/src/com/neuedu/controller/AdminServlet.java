package com.neuedu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import com.google.gson.Gson;
import com.neuedu.common.AdminOperationEnum;
import com.neuedu.common.Const;
import com.neuedu.common.ServerResponse;
import com.neuedu.service.IAdminService;
import com.neuedu.service.impl.AdminServiceImpl;
import com.nueedu.pojo.Admin;

/**
 * Servlet implementation class AdminServlet1
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doPost(request,response);
	}

	/**
	 * ��Ӧ�����ݸ�ʽjson 
	 * jsonͳһ��ʽ�� int status ״̬  0:������ýӿڳɹ� �������ص����ݷ�װ��data  1���û��������� 2���������
	 *             String msg����
	 *             object data 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		AdminServiceImpl adminServiceImpl =  AdminServiceImpl.GetInstance();
		PrintWriter pw = response.getWriter();
		String operation = request.getParameter("operation");
		if(operation==null) {
			//���ؿͻ��ˣ�opertion�������봫��->json
			//{"status":3,"msg":"opreation�������봫��"}
			String responseText = ServerResponse.CreateResponseByFail(3, "opreation�������봫��").obj2str();
			pw.write(responseText);
			pw.close();
		}
		try {
			int _operation=Integer.parseInt(operation);
			if(_operation==AdminOperationEnum.ADMIN_LOGIN.getOperation_type()) {//��½
			    String username = request.getParameter("username");
			    String password = request.getParameter("password");
			    ServerResponse<Admin> serverResponse = adminServiceImpl.login(username, password);
			    if(serverResponse.isSuccess()) {
			    	HttpSession session = request.getSession();
			    	session.setAttribute("admin", true);
			    	Cookie username_cookie = new Cookie("username",username);
			    	username_cookie.setMaxAge(7*24*3600);
			    	response.addCookie(username_cookie);
			    	Cookie password_cookie = new Cookie("password",password);
			    	password_cookie.setMaxAge(7*24*3600);
			    	response.addCookie(password_cookie);
			    	
			    }
			    String responseText=serverResponse.obj2str();
			    pw.write(responseText);
			    pw.close(); 
			}else if(_operation== AdminOperationEnum.ADMIN_REGISTER.getOperation_type()) {
				String username = request.getParameter("username");
			    String password = request.getParameter("password");
			    String responseText = adminServiceImpl.register(username, password).obj2str();
			   // String responseText="{\"status\":2,\"msg\":\"ע��ɹ�\",data:{username:"+username+",password:"+password+"}}";
			    pw.write(responseText);
				pw.close();
			}
		}catch(NumberFormatException e) {
			//���ؿͻ��ˣ�operation�����ǿͻ���
			//{"status":4,"msg":"operation����Ϊ����"}
			String responseText=ServerResponse.CreateResponseByFail(Const.OPERATION_NOT_NULL, "operation����Ϊ����").obj2str();
			pw.write(responseText);
			pw.close();
		}
	}
}
