package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.model.User;
import com.utils.HibernateUtil;

public class UserStorage {

	private static UserStorage instance;
	private static final Logger log = Logger.getLogger(UserStorage.class);

	public UserStorage() {
	}

	public static UserStorage getInstance() {
		if (instance == null)
			instance = new UserStorage();
		return instance;
	}

	public static void addUser(User user) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();

			log.debug("New user was added in DB!");
		} catch (Exception e) {
			log.error("Error added new user in DB! " + e);
		}
	}

	@SuppressWarnings("unchecked")
	public static List<User> findUser() {

		List<User> users = new ArrayList<User>();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			users = session.createQuery("from User").list();
			session.getTransaction().commit();
			
			log.debug("Getting users from DB!");
		} catch (Exception e) {
			log.error("Error getting users from DB! " + e);
		}
		return users;
	}

	public static boolean findUserByEmail(String email) {
		for (User user : findUser()){
			if (user.getEmail().equalsIgnoreCase(email)) return false;
		}
		return true;
	}
}
