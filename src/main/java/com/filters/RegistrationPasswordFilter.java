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

@WebFilter("/registration")
public class RegistrationPasswordFilter implements Filter {
	
	private static final String REDIRECT_PAGE = "registration.jsp";
	private static final String PASSWORD = "pass";
	private static final String CONFIRM_PASSWORD = "conf_pass";
	private static final String ERROR_MESSAGE = "Password is incorrect! Min 5, Max 25 signs! ";
	private static final String MESSAGE_ATRIBUTE = "message_reg_pass";
	private static final String ERROR_TYPE_ATRIBUTE = "error_message_reg";
	private static final String TYPE_ATRIBUTE = "type_register_pass";
	private static final String PASS_REGEX = "\\S{5,25}";
	
	private static final Logger log = Logger.getLogger(RegistrationPasswordFilter.class);

    public RegistrationPasswordFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String password = request.getParameter(PASSWORD);
		String confirm_password = request.getParameter(CONFIRM_PASSWORD);
		
		if (password.equals(confirm_password) && Pattern.matches(PASS_REGEX, password)) {
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
