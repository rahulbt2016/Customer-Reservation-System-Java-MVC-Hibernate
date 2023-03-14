package com.rt.model;

import javax.persistence.*;

/**
 * 
 * @author rahul
 * City class that maps to city table of database
 */
@Entity
@Table(name = "City")
public class City {

    @Id
    @Column(name = "cityCode")
    private String code;

    @Column(name = "cityName")
    private String name;

    //Getters and Setters
	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setName(String name) {
		this.name = name;
	}

    
}
