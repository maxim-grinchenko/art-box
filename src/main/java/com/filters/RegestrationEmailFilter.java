package com.filters;

import java.io.IOException;
import java.util.regex.Matcher;
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
public class RegestrationEmailFilter implements Filter {
	
	private static final String REDIRECT_PAGE = "home.jsp";
	private static final String EMAIL = "email";
	private static final String SUCCESS_MASSAGE = "Success!";
	private static final String ERROR_MASSAGE_PARAMETER = "Error with parameters!";
	private static final String EMAIL_NOT_VALID = "email is not valid!";
	private static final String MESSAGE_ATRIBUTE = "message";
	private static final String TYPE = "type";
	private static final String EMAIL_REGEX = "^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$";
	
	private static final Logger log = Logger.getLogger(RegistrationServlet.class);

    public RegestrationEmailFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		String email = request.getParameter(EMAIL);
		
		log.debug("get param RegestrationEmailFilter... " + " email : " + email);
		
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		
		log.debug(matcher);
		
		if (matcher.find()) {
			log.debug(SUCCESS_MASSAGE);
			chain.doFilter(request, response);
		} else {
			log.debug(EMAIL_NOT_VALID);
			
		}
		request.getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
