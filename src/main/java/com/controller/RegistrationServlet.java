package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

@WebServlet("")
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String REDIRECT_PAGE = "home.jsp";

	private static final String PASSWORD = "pass_v1";
	private static final String PASSWORD_CONFIRM = "pass_v2";
	
	private static final Logger log = Logger.getLogger(RegistrationServlet.class);
	
       
    public RegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(REDIRECT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String password = request.getParameter(PASSWORD);
		String passwordConfirm = request.getParameter(PASSWORD_CONFIRM);
		
//		log.debug("get param... " 
//				+ " email : " 				+ email 
//				+ " password : " 			+ password 
//				+ " password confirm : " 	+ passwordConfirm );
//		
		//TODO validade email
		
		
		
		//TODO validade pass 1 and  pass 2
		//TODO add user in UserStorage
		
		
	}

}
