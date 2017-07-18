package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.dao.ArtBoxStorage;
import com.manager.ConfigKey;
import com.manager.PropertiesLoader;

@WebServlet("/remove")
public class RemoveServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String PRODUCTS = "products";
	
	private static final Logger log = Logger.getLogger(RemoveServlet.class);

    public RemoveServlet() {
        super();
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message;
		String type = PropertiesLoader.getProperty(ConfigKey.RED.name());

		try {
			
			final String ARTBOX_ID = "id";
			int id = Integer.parseInt(request.getParameter(ARTBOX_ID));

			log.debug("Get parameter to delete: " + id);
			
			ArtBoxStorage storage = ArtBoxStorage.getInstance();
			storage.deleteArtBox(id);
			
			request.setAttribute(PRODUCTS, storage.getList());
			
			final String _SUCCESS_REMOVED_BEFORE 	= PropertiesLoader.getProperty(ConfigKey.SUCCESS_REMOVED_BEFORE.name());
			final String _SUCCESS_REMOVED_AFTER 	= PropertiesLoader.getProperty(ConfigKey.SUCCESS_REMOVED_AFTER.name());
			
			message = _SUCCESS_REMOVED_BEFORE + id + _SUCCESS_REMOVED_AFTER;
			type = PropertiesLoader.getProperty(ConfigKey.GREEN.name());
			log.debug(message);
			
		} catch (NumberFormatException e) {
			message = PropertiesLoader.getProperty(ConfigKey.CANT_REMOVED.name());
			log.error(message, e);
		} catch (NullPointerException e) {
			message = PropertiesLoader.getProperty(ConfigKey.CANT_REMOVED.name());
			log.error(message, e);
		}
		
		request.setAttribute("message", message);
		request.setAttribute("type", type);
		request.getRequestDispatcher(PropertiesLoader.getProperty(ConfigKey.DASHBOARD_PAGE.name())).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
