package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.dao.UserStorage;
import com.model.ArtBoxUser;

@WebServlet("/authorization")
public class AuthorizationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String HOME_PAGE = "home.jsp";
	private static final String AUTHORIZATION_PAGE = "authorization.jsp";
	private static final String EMAIL = "email";
	private static final String PASSWORD = "pass";
	private static final String HIDDEN = "hidden";
	private static final String SUCCCESS_AUTH = "SUCCESS AUTHORIZATION!";
	private static final String BLOCK_MESSAGE_REGISTER = "block_message_register";
	private static final String ERROR_AUTH = "User not found!";
	private static final String GREEN = "green";
	private static final String RED = "red";
	private static final Logger log = Logger.getLogger(AuthorizationServlet.class);
	
	//private final PropertiesLoader propertiesLoader = new PropertiesLoaderImpl();

	public AuthorizationServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect(HOME_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = request.getParameter(EMAIL);
		String password = request.getParameter(PASSWORD);

		log.debug("get param ... email : " + email + "/ password : " + password);

		boolean successAuthorization = false;
		
		for (ArtBoxUser user : UserStorage.findUser()) {
			if (email.equals(user.getEmail()) && password.equals(user.getPassword())) {
				log.debug("user success authorization!");
				successAuthorization = true;
				
				Cookie cookie = new Cookie("ArtBoxCookie", email);
				response.addCookie(cookie);
				log.debug("add cookie : " + cookie);
				
				HttpSession session = request.getSession();
				session.setMaxInactiveInterval(3600*3600);
				session.setAttribute("user", "Hello "+user.getName() + " | ");
				session.setAttribute("hidden", "hidden");
				session.setAttribute("logout", "logout");
				request.setAttribute("user", "Hello " + user.getName() + " | ");
				request.setAttribute("logout", "logout");
				log.debug(session.getId());
				break;
			}
		}

		if (successAuthorization) {
			log.debug("SUCCESS AUTHORIZATION!");
			request.setAttribute("success_message_register", SUCCCESS_AUTH);
			request.setAttribute("block_message_register", BLOCK_MESSAGE_REGISTER);
			request.setAttribute("green", GREEN);
			request.setAttribute("hidden", HIDDEN);
			request.getRequestDispatcher(HOME_PAGE).forward(request, response);
		} else {
			log.debug("ERROR AUTHORIZATION!");
			request.setAttribute("message", ERROR_AUTH);
			request.setAttribute("type", RED);
			request.getRequestDispatcher(AUTHORIZATION_PAGE).forward(request, response);
		}
	}

}
