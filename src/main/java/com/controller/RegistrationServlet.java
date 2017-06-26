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
	private static final String REDIRECT_PAGE = "home.jsp";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "pass";
	private static final String SUCCESS_MESSAGE = "Congratulation! You registered is succesfuly!";
	private static final String MESSAGE_ATRIBUTE = "message_register";
	private static final String SUCCESS_ATRIBUTE = "success_message_register";
	private static final String TYPE = "type_register";
	
	
	private static final Logger log = Logger.getLogger(RegistrationServlet.class);
	
    public RegistrationServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(REDIRECT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter(PASSWORD);
		String email = request.getParameter(EMAIL);
		
		
		log.debug("get param ..." 
				+ " email : "		+ email 
				+ "/ password : "	+ password);
		
		UserStorage.addNewUser(new UserBuilder()
									.setUserId()
									.setUserEmail(email)
									.setUserPassword(password)
									.getUserBuild());
		
		request.setAttribute(MESSAGE_ATRIBUTE, SUCCESS_MESSAGE);
		request.setAttribute(TYPE, SUCCESS_ATRIBUTE);
		request.setAttribute("type_hidden", "hiden_mess_register");
		request.getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
		
	}

}
