package com.rt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rt.dao.CityDao;
import com.rt.dao.CustomerDao;
import com.rt.dao.ReservationDao;
import com.rt.model.City;
import com.rt.model.Customer;
import com.rt.model.Reservation;

/**
 * Servlet implementation class AddReservation
 */
@WebServlet("/AddReservation")
public class AddReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReservation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String travelDate = request.getParameter("travel-date");
		String origin = request.getParameter("origin");
		String destination = request.getParameter("destination");
		String customerId = request.getParameter("customer-id");
		
		Reservation reservation = new Reservation();
		reservation.setTravelDate(travelDate);
		
		ReservationDao reservationDao = new ReservationDao();
		reservationDao.addReservation(reservation, Integer.parseInt(customerId), origin, destination);
		
		CustomerDao customerDao = new CustomerDao();
		Customer customer = customerDao.getCustomerByCustomerId(Integer.parseInt(customerId));
		
		request.setAttribute("customer", customer);
		
		CityDao cityDao = new CityDao();
		List<City> cities = cityDao.getAllCities();
		request.setAttribute("cities", cities);
		
		List<Reservation> reservations = reservationDao.getReservationsByCustomerId(Integer.parseInt(customerId));
		request.setAttribute("reservations", reservations);
		
		request.getRequestDispatcher("reservation.jsp").forward(request, response);
		
		
	}

}
