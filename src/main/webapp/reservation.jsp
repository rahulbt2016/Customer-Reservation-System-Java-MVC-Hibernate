<%@page import="com.rt.dao.ReservationDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ page import="com.rt.model.*"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reservation</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css"
	integrity="sha512-SzlrxWUlpfuzQ+pcUCosxcglQRNAq/DZjVsC0lE40xsADsfeQoEypE+enwcOiGjk/bSuGGKHEyjSoQ1zVisanQ=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<style type="text/css">
</style>
</head>
<body>
	<%-- Get the value of the "id" parameter --%>
	<%
		Customer customer = (Customer) request.getAttribute("customer");
		List<City> cities = (List<City>) request.getAttribute("cities");
		List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
	%>
	
	<span>
		<a href="${pageContext.request.contextPath }/" class="btn btn-danger mt-5 mr-5" style="font-size: 18px; float: right">
			Logout&nbsp;<i class="fas fa-sign-out"></i>
		</a>
	</span>
	
	<div class="row">
		<div class="col-lg-5 mt-5 ml-5">
			<div class="card mb-4" style="background-color:lavender">
				<div class="card-body">
					<div class="row">
						<div class="col-sm-3">
							<p class="mb-0">Full Name</p>
						</div>
						<div class="col-sm-9">
							<p class="text-muted mb-0"><%=customer.getName()%></p>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-3">
							<p class="mb-0">Email</p>
						</div>
						<div class="col-sm-9">
							<p class="text-muted mb-0"><%=customer.getEmail()%></p>
						</div>
					</div>
					<hr>
					<div class="row">
						<div class="col-sm-3">
							<p class="mb-0">Date of Birth</p>
						</div>
						<div class="col-sm-9">
							<p class="text-muted mb-0"><%=customer.getDob()%></p>
						</div>
					</div>

				</div>
			</div>
			
			
			<div>
				<h2>My Reservations</h2>
				<br>
				
				<table class="table table-striped table-bordered table-hover table-active">
			<thead class="thead-dark">
				<tr>
					<th>RID</th>
					<th>Travel Date</th>
					<th>Origin</th>
					<th>Destination</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reservations}" var="reservation">
					<tr>
						<td>${reservation.getId()}</td>
						<td>${reservation.getTravelDate()}</td>
						<td> ${reservation.getOriginCity().getCode()} </td>
						<td> ${reservation.getDestinationCity().getCode()} </td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
				
			</div>
			
		</div>

		<div class="col-lg-6 mt-5">
			<h2>Reservation Form</h2>
			<br>
			<form action="${pageContext.request.contextPath }/AddReservation" method="post">
			<input type="text" name="customer-id" hidden value="${customer.getId()}">
			<div class="form-group">
				<label for="travel-date">Travel Date:</label>
				<input type="date" class="form-control" id="travel-date"  name="travel-date">
			</div>
			<div class="form-group">
				<label for="origin">Origin:</label>
				<select class="form-control" id="origin" name="origin">
					 <c:forEach var="city" items="${cities}">
					    <option value="${city.getCode()}">${city.getName()}</option>
					  </c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="destination">Origin:</label>
				<select class="form-control" id="destination" name="destination">
					 <c:forEach var="city" items="${cities}">
					    <option value="${city.getCode()}">${city.getName()}</option>
					  </c:forEach>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
		</div>


	</div>
</body>
</html>
