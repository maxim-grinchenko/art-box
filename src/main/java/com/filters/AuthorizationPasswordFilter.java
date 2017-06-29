package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.log4j.Logger;

import com.utils.Utils;

@WebFilter("/authorization")
public class AuthorizationPasswordFilter implements Filter {
	
	private static final String REDIRECT_PAGE = "authorization.jsp";
	private static final String PASSWORD = "pass";
	private static final String ERROR_MESSAGE = "Password is incorrect!";
	private static final String MESSAGE_ATRIBUTE = "message_reg_pass";
	private static final String ERROR_TYPE_ATRIBUTE = "error_message_reg";
	private static final String TYPE_ATRIBUTE = "type_register_pass";
	
	private static final Logger log = Logger.getLogger(AuthorizationPasswordFilter.class);

    public AuthorizationPasswordFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String password = request.getParameter(PASSWORD);
		
		if (Utils.passwordVerification(password)) {
			log.debug("password is correct!");
			chain.doFilter(request, response);
		} else {
			log.debug("password is incorrect!");
			request.setAttribute(MESSAGE_ATRIBUTE, ERROR_MESSAGE);
			request.setAttribute(TYPE_ATRIBUTE, ERROR_TYPE_ATRIBUTE);
			request.getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
