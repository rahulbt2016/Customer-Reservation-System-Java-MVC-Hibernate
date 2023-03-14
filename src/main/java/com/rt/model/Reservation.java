package com.rt.model;

import javax.persistence.*;

/**
 * 
 * @author rahul
 * Reservation class that maps to reservation table of database
 */
@Entity
@Table(name = "Reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservationId")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;

    @Column(name = "travelDate")
    private String travelDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "originCityCode")
    private City originCity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destinationCityCode")
    private City destinationCity;

    

    // Getters and setters
    
	public int getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public String getTravelDate() {
		return travelDate;
	}

	public City getOriginCity() {
		return originCity;
	}

	public City getDestinationCity() {
		return destinationCity;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setTravelDate(String travelDate) {
		this.travelDate = travelDate;
	}

	public void setOriginCity(City originCity) {
		this.originCity = originCity;
	}

	public void setDestinationCity(City destinationCity) {
		this.destinationCity = destinationCity;
	}

}
