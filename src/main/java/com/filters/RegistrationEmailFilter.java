package com.filters;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

import com.controller.RegistrationServlet;

@WebFilter("/registration")
public class RegistrationEmailFilter implements Filter {
	
	private static final String REDIRECT_PAGE = "registration.jsp";
	private static final String EMAIL = "email";
	private static final String ERROR_MESSAGE = "Your email is incorrect!";
	private static final String MESSAGE_ATRIBUTE = "message_reg_email";
	private static final String ERROR_TYPE = "error_message_reg";
	private static final String TYPE_ATRIBUTE = "type_reg_email";
	private static final String EMAIL_REGEX = "^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$";
	
	private static final Logger log = Logger.getLogger(RegistrationServlet.class);

    public RegistrationEmailFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String email = request.getParameter(EMAIL);
		
		log.debug("get param RegestrationEmailFilter... " + " email : " + email);
		
		if (Pattern.matches(EMAIL_REGEX, email)) {
			log.debug("Success! email is incorrect!");
			chain.doFilter(request, response);
		} else {
			log.debug("email is not valid!");
			request.setAttribute(MESSAGE_ATRIBUTE, ERROR_MESSAGE);
			request.setAttribute(TYPE_ATRIBUTE, ERROR_TYPE);
			request.getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
