package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.builder.UserBuilder;
import com.dao.UserStorage;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String HOME_PAGE = "home.jsp";	
	private static final String AUTHORIZATION_PAGE = "authorization.jsp";
	private static final String NAME = "name";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "pass";
	private static final String SUCCESS_REG = "Congratulation! You registered is successfully!";
	private static final String GREEN = "green";
	private static final Logger log = Logger.getLogger(RegistrationServlet.class);
	
    public RegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(HOME_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter(NAME);
		String password = request.getParameter(PASSWORD);
		String email = request.getParameter(EMAIL);
		
		log.debug("get param ... email : " + email + "/ password : " + password);
		
		UserStorage.addNewUser(new UserBuilder()
									.setUserId()
									.setUserName(name)
									.setUserEmail(email)
									.setUserPassword(password)
									.getUserBuild());
		
		request.setAttribute("message", SUCCESS_REG);
		request.setAttribute("type", GREEN);
		request.getRequestDispatcher(AUTHORIZATION_PAGE).forward(request, response);
	}

}
