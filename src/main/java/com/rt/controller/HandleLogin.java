package com.rt.controller;

import java.io.IOException;
import java.util.Iterator;
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
 * Servlet implementation class HandleLogin
 */
@WebServlet("/reservation")
public class HandleLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HandleLogin() {
        super();
        // TODO Auto-generated constructor stub
    }



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		CustomerDao customerDao = new CustomerDao();
		List <Customer> customers = customerDao.getCustomers();
		
		Customer loggedInCustomer = null;
		
		for (Iterator iterator = customers.iterator(); iterator.hasNext();) {
			Customer customer = (Customer) iterator.next();
			
			if (customer.getEmail().toLowerCase().equals(email.toLowerCase()) && 
					customer.getPassword().equals(password)) {
				
				loggedInCustomer = customer;
				break;
			}

		}
		
		if(loggedInCustomer != null) {
			
			request.setAttribute("customer", loggedInCustomer);
			
			CityDao cityDao = new CityDao();
			List<City> cities = cityDao.getAllCities();
			request.setAttribute("cities", cities);
			
			ReservationDao reservationDao = new ReservationDao();
			List<Reservation> reservations = reservationDao.getReservationsByCustomerId(loggedInCustomer.getId());
			//System.out.println(reservations.get(1).getOriginCity().getName());
			request.setAttribute("reservations", reservations);
			
			request.getRequestDispatcher("reservation.jsp").forward(request, response);
			
		}
		else {
			response.sendRedirect("loginFailed.jsp");
		}
	}

}
