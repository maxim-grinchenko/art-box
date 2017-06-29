package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dao.ArtBoxStorage;
import com.model.ArtBox;
import com.utils.Utils;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ARTBOX_NAME = "name";
	private static final String ARTBOX_AGE = "age";
	private static final String ARTBOX_COST = "cost";
	private static final String REDIRECT_PAGE = "add.jsp";
	private static final String MESSAGE_ATRIBUTE = "message";
	private static final String TYPE = "type";
	private static final String ERROR_MASSAGE = "Error with parameters!";
	private static final String INVALID_MASSAGE = " It can't be negative!";
	private static final String SUCCESS_MASSAGE_BEGIN = "Success! Pruduct ";
	private static final String SUCCESS_MASSAGE_END = " was added.";
	private static final String SUCCESS_ATRIBUTE = "success_message";
	private static final String ERROR_MESSAGE_ATRIBUTE = "error_message";
	
	private static final Logger log = Logger.getLogger(AddServlet.class);

	public AddServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect(REDIRECT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String message;
		String messageType = ERROR_MESSAGE_ATRIBUTE;

		try {
			
			String name = request.getParameter(ARTBOX_NAME);
			int age = Integer.parseInt(request.getParameter(ARTBOX_AGE));
			double cost = Double.parseDouble((request.getParameter(ARTBOX_COST)).replaceAll(" ","").replace(',','.'));
			
			log.debug("get params... name - " + name + "; age - " + age + "; cost - " + cost);

			if (Utils.isValidParameters(age, cost)) {
				message = ERROR_MASSAGE + INVALID_MASSAGE;
				log.warn(message);
			} else {

				ArtBox newArtBox = new ArtBox(name, age, cost);

				ArtBoxStorage artboxStorage = ArtBoxStorage.getInstance();
				artboxStorage.addedBase(newArtBox);

				message = SUCCESS_MASSAGE_BEGIN + name + SUCCESS_MASSAGE_END;
				messageType = SUCCESS_ATRIBUTE;

				log.debug(message);
			}

		} catch (NumberFormatException e) {
			message = ERROR_MASSAGE;
			log.error(message);
		}

		request.setAttribute(MESSAGE_ATRIBUTE, message);
		request.setAttribute(TYPE,messageType);
		request.getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
	}
}
