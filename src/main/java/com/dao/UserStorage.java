package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.model.ArtBoxUser;

public class UserStorage {
	
	private static List<ArtBoxUser> users = new ArrayList<ArtBoxUser>();
	private static UserStorage instance;
	private static final Logger log = Logger.getLogger(UserStorage.class);
	
	public UserStorage(){
	}
	
	public static UserStorage getInstance (){
		if(instance == null) instance = new UserStorage();
		return instance;
	}

	public static void addNewUser(ArtBoxUser user) {
		users.add(user);
		log.info("New user added! : " + user.toString());
	}
	
	public static List<ArtBoxUser> getAllUsers(){
		return users;
	}
}
