package com.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.model.ArtBox;

public class ArtBoxStorage {

	private Map<Integer, ArtBox> unitBox = new ConcurrentHashMap<Integer, ArtBox>();
	private static ArtBoxStorage instance;
	private AtomicInteger counter = new AtomicInteger(0);

	private ArtBoxStorage() {
	}

	public static ArtBoxStorage getInstance() {
		if (instance == null) {
			instance = new ArtBoxStorage();
		}
		return instance;
	}

	public void addedBase(ArtBox newArtbox) {
		Integer id = null;
		id = counter.incrementAndGet();
		this.unitBox.put(id, newArtbox);
		System.out.println("id : " + id + " " + newArtbox);
	}

	public Map<Integer, ArtBox> removedBase() {
		return this.unitBox;
	}

	public Map<Integer, ArtBox> getAll() {
		return this.unitBox;
	}

	public Set<Map.Entry<Integer, ArtBox>> findArtBoxByName(String searchArtBoxByName) {

		Map<Integer, ArtBox> serchCollection = new HashMap<Integer, ArtBox>();

		for (Map.Entry<Integer, ArtBox> findArtBox : unitBox.entrySet()) {
			if (findArtBox.getValue().getName().equalsIgnoreCase(searchArtBoxByName)) {
				serchCollection.put(findArtBox.getKey(), findArtBox.getValue());
			}
		}
		return serchCollection.isEmpty() ? null : serchCollection.entrySet();
	}
}
