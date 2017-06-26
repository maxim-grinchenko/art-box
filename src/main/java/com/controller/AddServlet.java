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

@WebServlet("/add")
public class AddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ARTBOX_NAME = "name";
	private static final String ARTBOX_AGE = "age";
	private static final String ARTBOX_COST = "cost";
	private static final String REDIRECT_PAGE = "add.jsp";
	private static final String MESSAGE_ATRIBUTE = "message";
	private static final String TYPE = "type";
	private static final String ERROR_MASSAGE_PARAMETER = "Error with parameters!";
	private static final String INVALID_MASSAGE_PARAMETER = " It can't be negative!";
	private static final String ERROR_MESSAGE_ATRIBUTE = "error_message";
//	private static final String ERROR_MASSAGE_BEGIN = "Error! Product ";
//	private static final String ERROR_MASSAGE_END = " can't be added due to ";
	private static final String SUCCESS_MASSAGE_BEGIN = "Success! Pruduct ";
	private static final String SUCCESS_MASSAGE_END = " was added.";
	private static final String SUCCESS_MASSAGE_ATRIBUTE = "success_message";
	
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
		String typeAtribute = ERROR_MESSAGE_ATRIBUTE;

		try {
			
			String name = request.getParameter(ARTBOX_NAME);
			int age = Integer.parseInt(request.getParameter(ARTBOX_AGE));
			double cost = Double.parseDouble((request.getParameter(ARTBOX_COST)).replaceAll(" ","").replace(',','.'));
			
			log.debug("get params... name - " + name + "; age - " + age + "; cost - " + cost);

			if (isValidedParameters(age, cost)) {
				message = ERROR_MASSAGE_PARAMETER + INVALID_MASSAGE_PARAMETER;
				log.warn(message);
			} else {

				ArtBox newArtBox = new ArtBox(name, age, cost);

				ArtBoxStorage artboxStorage = ArtBoxStorage.getInstance();
				artboxStorage.addedBase(newArtBox);

				message = SUCCESS_MASSAGE_BEGIN + name + SUCCESS_MASSAGE_END;
				typeAtribute = SUCCESS_MASSAGE_ATRIBUTE;

				log.debug(message);
			}

		} catch (NumberFormatException e) {
			message = ERROR_MASSAGE_PARAMETER;
			log.error(message);
		}

		request.setAttribute(MESSAGE_ATRIBUTE, message);
		request.setAttribute(TYPE,typeAtribute);
		request.getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
	}
	
	private boolean isValidedParameters(int age, double cost) {	
		return cost > 0 && age > 0 ? false : true;
	}
	
}
