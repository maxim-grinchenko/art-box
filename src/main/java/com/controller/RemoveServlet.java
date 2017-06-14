package com.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ArtBoxStorage;
import com.model.ArtBox;

@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String ARTBOX_ID = "id";
	private static final String DASHBOARD_PAGE = "dashboard.jsp";
	private static final String MESSAGE_ATRIBUTE = "message";
	private static final String TYPE = "type";
	private static final String PRODUCTS = "products";
	private static final String ERROR_MESSAGE_ATRIBUTE = "error_message";
	private static final String ERROR_WRONG = " can't be removed due to....";
	private static final String ERROR_NOT_FOUND = " can't be removed due to....";
	private static final String SUCCESS_REMOVED_AFTER = " was removed successfully!";
	private static final String SUCCESS_REMOVED_BEFORE = "Product with id ";
	private static final String SUCCESS_MASSAGE_ATRIBUTE = "success_message";

    public RemoveServlet() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message;
		String typeAtribute = ERROR_MESSAGE_ATRIBUTE;

		try {
			int id = Integer.parseInt(request.getParameter(ARTBOX_ID));

			ArtBoxStorage storage = ArtBoxStorage.getInstance();
			Map<Integer, ArtBox> unitBox = storage.removedBase();

			unitBox.remove(id);
			
			request.setAttribute(PRODUCTS, unitBox.entrySet());
			
			message = SUCCESS_REMOVED_BEFORE + id + SUCCESS_REMOVED_AFTER;
			typeAtribute = SUCCESS_MASSAGE_ATRIBUTE;
			
		} catch (NumberFormatException e) {
			message = ERROR_WRONG;
		} catch (NullPointerException e) {
			message = ERROR_NOT_FOUND;
		}
		
		request.setAttribute(MESSAGE_ATRIBUTE, message);
		request.setAttribute(TYPE,typeAtribute);
		request.getRequestDispatcher(DASHBOARD_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
