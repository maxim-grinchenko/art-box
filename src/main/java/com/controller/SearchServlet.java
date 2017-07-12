package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dao.ArtBoxStorage;
import com.model.ArtBox;


@WebServlet("/find")
public class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String REDIRECT_PAGE = "dashboard.jsp";
	private static final String ARTBOX_THEME = "name";
	private static final String MESSAGE_ATRIBUTE = "message";
	private static final String TYPE = "type";
	private static final String PRODUCTS = "products";
	private static final String ERROR_NO_DB_BEGIN = "ERROR! Product ";
	private static final String ERROR_NO_DB_AFTER = " not found in DB!";
	private static final String ERROR_WRONG = "Somesing is wrong";	
	private static final String ERROR_MESSAGE_ATRIBUTE = "error_message";
	private static final String SUCCESS_FOUND = "List of found : ";	
	private static final String SUCCESS_MASSAGE_ATRIBUTE = "success_message";
	
	private static final Logger log = Logger.getLogger(SearchServlet.class);
       
    public SearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = null;
		String typeAtribute = ERROR_MESSAGE_ATRIBUTE;

		try {
			String name = request.getParameter(ARTBOX_THEME);
			log.debug("get search parameter : " + name);
			
			ArtBoxStorage artboxStorage = ArtBoxStorage.getInstance();
			List<ArtBox> findArtBoxCollections = artboxStorage.findArtBoxByName(name);
			log.debug("*******************"+findArtBoxCollections);
			boolean notFound = true;

			if (findArtBoxCollections != null) {
				log.info("collections arbox not null");
				
				request.setAttribute(PRODUCTS, findArtBoxCollections);

				message = SUCCESS_FOUND + name;
				typeAtribute = SUCCESS_MASSAGE_ATRIBUTE;
				
				notFound = false;
			}

			if (notFound){
				message = ERROR_NO_DB_BEGIN + name + ERROR_NO_DB_AFTER;
			}
				
			log.debug("Displaying of found parameters...");

		} catch (NullPointerException e) {
			message = ERROR_WRONG;
			log.error(message, e);
			
			response.sendRedirect(REDIRECT_PAGE);
		}

		request.setAttribute(MESSAGE_ATRIBUTE, message);
		request.setAttribute(TYPE, typeAtribute);
		request.getRequestDispatcher(REDIRECT_PAGE).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
