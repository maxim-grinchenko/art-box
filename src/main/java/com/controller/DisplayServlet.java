package com.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dao.ArtBoxStorage;
import com.model.ArtBox;

@WebServlet("/dashboard")
public class DisplayServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String DASHBOARD_PAGE = "dashboard.jsp";
	private static final String PRODUCTS = "products";
	private static final String MESSAGE_ATRIBUTE = "message";
	private static final String TYPE = "type";
	private static final String LIST_IS_EMPTY = "The list is empty!";
	private static final String ERROR_MESSAGE_ATRIBUTE = "error_message";
	
	private static final Logger log = Logger.getLogger(DisplayServlet.class);
	
	public DisplayServlet() {
		super();
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("home.jsp");
		} else {

			ArtBoxStorage artboxStorage = ArtBoxStorage.getInstance();
			Map<Integer, ArtBox> collectionsArtBox = artboxStorage.getAll();

			String typeAtribute = ERROR_MESSAGE_ATRIBUTE;

			if (collectionsArtBox.isEmpty() || collectionsArtBox == null) {
				request.setAttribute(MESSAGE_ATRIBUTE, LIST_IS_EMPTY);
				log.debug(LIST_IS_EMPTY);
			} else {
				request.setAttribute(PRODUCTS, collectionsArtBox.entrySet());
				log.debug("Display collections ArtBox...");
			}

			request.setAttribute(TYPE, typeAtribute);
			request.getRequestDispatcher(DASHBOARD_PAGE).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
