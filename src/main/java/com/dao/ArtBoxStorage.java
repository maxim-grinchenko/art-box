package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.model.ArtBox;
import com.utils.HibernateUtil;

public class ArtBoxStorage {

	private static ArtBoxStorage instance;

	private static final Logger log = Logger.getLogger(ArtBoxStorage.class);

	private ArtBoxStorage() {
	}

	public static ArtBoxStorage getInstance() {
		if (instance == null) {
			instance = new ArtBoxStorage();
		}
		return instance;
	}

	public void addedBase(ArtBox newArtBox) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			session.save(newArtBox);
			session.getTransaction().commit();

			log.debug("New ArtBox was added in DB!");
		} catch (Exception e) {
			log.error("Error added new ArtBox in DB! " + e);
		}
	}

	public void deleteArtBox(int id) {
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			ArtBox artBox = session.get(ArtBox.class, id);
			session.delete(artBox);
			session.getTransaction().commit();

			log.debug(artBox + " was delete in DB!");
		} catch (Exception e) {
			log.error("Error delete ArtBox in DB! " + e);
		}
	}

	@SuppressWarnings("unchecked")
	public List<ArtBox> getList() {
		
		List<ArtBox> artBoxs = new ArrayList<ArtBox>();

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			session.beginTransaction();
			artBoxs = session.createQuery("from ArtBox").list();
			session.getTransaction().commit();
			
			log.debug("Getting ArtBox from DB!");
		} catch (Exception e) {
			log.error("Error getting ArtBox from DB! " + e);
		}
		return artBoxs;
	}

	public List<ArtBox> findArtBoxByName(String artBoxName) {

		List<ArtBox> artBoxs = new ArrayList<>();
		
		for (ArtBox artBox : getList()) {
			if (artBox.getName().equalsIgnoreCase(artBoxName)) {
				artBoxs.add(artBox);
			}
		}
		return artBoxs.isEmpty() ? null : artBoxs;
	}
}
