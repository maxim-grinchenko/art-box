package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dao.ArtBoxStorage;

@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String ARTBOX_ID = "id";
	private static final String REDIRECT_PAGE = "dashboard.jsp";
	private static final String MESSAGE_ATRIBUTE = "message";
	private static final String TYPE = "type";
	private static final String PRODUCTS = "products";
	private static final String ERROR_MESSAGE_ATRIBUTE = "error_message";
	private static final String ERROR_WRONG = " can't be removed due to....";
	private static final String ERROR_NOT_FOUND = " can't be removed due to....";
	private static final String SUCCESS_REMOVED_AFTER = " was removed successfully!";
	private static final String SUCCESS_REMOVED_BEFORE = "Product with id ";
	private static final String SUCCESS_MASSAGE_ATRIBUTE = "success_message";
	
	private static final Logger log = Logger.getLogger(RemoveServlet.class);

    public RemoveServlet() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message;
		String typeAtribute = ERROR_MESSAGE_ATRIBUTE;

		try {
			int id = Integer.parseInt(request.getParameter(ARTBOX_ID));

			log.debug("Get parameter to delete: " + id);
			
			ArtBoxStorage storage = ArtBoxStorage.getInstance();
			storage.removedBase(id);
			
			request.setAttribute(PRODUCTS, storage.getList());
			
			message = SUCCESS_REMOVED_BEFORE + id + SUCCESS_REMOVED_AFTER;
			typeAtribute = SUCCESS_MASSAGE_ATRIBUTE;
			log.debug(message);
			
		} catch (NumberFormatException e) {
			message = ERROR_WRONG;
			log.error(message, e);
		} catch (NullPointerException e) {
			message = ERROR_NOT_FOUND;
			log.error(message, e);
		}
		
		request.setAttribute(MESSAGE_ATRIBUTE, message);
		request.setAttribute(TYPE,typeAtribute);
		request.getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
