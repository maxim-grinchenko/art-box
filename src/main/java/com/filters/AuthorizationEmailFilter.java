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
public class AuthorizationEmailFilter implements Filter {
	
	private static final String REDIRECT_PAGE = "authorization.jsp";
	private static final String EMAIL = "email";
	private static final String ERROR_MESSAGE = "Your email is incorrect!";
	private static final String MESSAGE_ATRIBUTE = "message_reg_email";
	private static final String ERROR_TYPE = "error_message_reg";
	private static final String TYPE_ATRIBUTE = "type_reg_email";
	
	private static final Logger log = Logger.getLogger(AuthorizationEmailFilter.class);

    public AuthorizationEmailFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			String email = request.getParameter(EMAIL);

			if (Utils.emailVerification(email)) {
				log.debug("email is correct!");
				chain.doFilter(request, response);
			} else {
				log.debug("email is INcorrect!");
				request.setAttribute(MESSAGE_ATRIBUTE, ERROR_MESSAGE);
				request.setAttribute(TYPE_ATRIBUTE, ERROR_TYPE);
				request.getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
			}

		} catch (NullPointerException e) {
			request.getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
