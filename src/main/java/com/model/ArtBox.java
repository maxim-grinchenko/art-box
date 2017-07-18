package com.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "products")
public class ArtBox implements Serializable{
	
	private static final long serialVersionUID = 1394408669538656589L;

	@Id
	@Column
	@GenericGenerator (name = "auto_inc", strategy = "increment")
	private int id;
	
	@Column
	private String name;
	
	@Column
	private int age;
	
	@Column
	private double cost;
	
	public ArtBox(){
		
	}
	
	public ArtBox (String name, int age, double cost){
		this.name = name;
		this.age = age;
		this.cost = cost;
	}
	
	public ArtBox (int id, String name, int age, double cost){
		this(name, age, cost);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "ArtBox [name=" + name + ", age=" + age + ", cost=" + cost + "]";
	}
}
