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

import com.controller.RegistrationServlet;
import com.utils.Utils;

@WebFilter("/registration")
public class RegistrationEmailFilter implements Filter {
	
	private static final String REDIRECT_PAGE = "registration.jsp";
	private static final String EMAIL = "email";
	private static final String ERROR_MESSAGE = "Your email is incorrect!";
	private static final String MESSAGE_ATRIBUTE = "message_reg_email";
	private static final String RED = "error_message_reg";
	private static final String TYPE = "type_reg_email";
	
	private static final Logger log = Logger.getLogger(RegistrationServlet.class);

    public RegistrationEmailFilter() {
    }

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//TODO output of different messages for emailVerification() and checkForUniquenessOfEmail()
		
		try {
			String email = request.getParameter(EMAIL);

			if (Utils.emailVerification(email) && Utils.checkForUniquenessOfEmail(email)) {
				log.debug("Success! email is correct!");
				chain.doFilter(request, response);
			} else {
				log.debug("email is not valid!");
				request.setAttribute(MESSAGE_ATRIBUTE, ERROR_MESSAGE);
				request.setAttribute(TYPE, RED);
				request.getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
			}

		} catch (NullPointerException e) {
			request.getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
		}
		
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
