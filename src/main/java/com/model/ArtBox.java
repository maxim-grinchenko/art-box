package com.model;

public class ArtBox {
	
	private String name;
	private int age;
	private double cost;
	
	public ArtBox (String name, int age, double cost){
		this.name = name;
		this.age = age;
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "ArtBox [name=" + name + ", age=" + age + ", cost=" + cost + "]";
	}
}
