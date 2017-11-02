<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	<div class="container">
		<div class="row">
		    <div class="col-md-4"></div>
		    <div class="col-md-4">
	    	<% 
    		HttpSession s = request.getSession(); 
    		String blankError = s.getAttribute("blankError") == null ? null : (String)s.getAttribute("blankError");
    		String creditCardError = s.getAttribute("creditCardError") == null ? null : (String)s.getAttribute("creditCardError");
    		String pinError = s.getAttribute("pinError") == null ? null : (String)s.getAttribute("pinError");
    		String amountError = s.getAttribute("amountError") == null ? null : (String)s.getAttribute("amountError");
    		String dateError = s.getAttribute("dateError") == null ? null : (String)s.getAttribute("dateError");
    		String charError = s.getAttribute("charError") == null ? null : (String)s.getAttribute("charError"); 
    		 
    		 %>

	    	<h1>Process Card</h1>
	    	
	    	<% if(blankError != null && blankError != "") { %>
	    		<div class="alert alert-danger">
						<%= blankError %> 
				</div>
	    	<%} %>
	    	<% if(creditCardError != null && creditCardError != "") { %>
	    		<div class="alert alert-danger">
						<%= creditCardError %> 
				</div>
	    	<%} %>
	    	<% if(pinError != null && pinError != "") { %>
	    		<div class="alert alert-danger">
						<%= pinError %> 
				</div>
	    	<%} %>
	    	<% if(amountError != null && amountError != "") { %>
	    		<div class="alert alert-danger">
						<%= amountError %> 
				</div>
	    	<%} %>
	    	<% if(dateError != null && dateError != "") { %>
	    		<div class="alert alert-danger">
						<%= dateError %> 
				</div>
	    	<%} %>
	    	<% if(charError != null && charError != "") { %>
	    		<div class="alert alert-danger">
						<%= charError %> 
				</div>
	    	<%} %>
	    	<form action="ValidateFormData.jsp" method="POST">
			  <div class="form-group">
			    <label for="creditCardNumber">Credit Card Number</label>
			    <input type="text" required class="form-control" id="creditCardNumber" aria-describedby="emailHelp" name="creditCardNumber" placeholder="Enter Credit Card Number">
			  </div>
			  <div class="form-group">
			    <label for="creditCardType">Credit Card Type</label>
			    <select class="form-control" required name="creditCardType" id="creditCardType">
			      <option value="1">Visa</option>
			      <option value="2">Master Card</option>
			      <option value="3">American Express</option>
			      <option value="4">Diners Club</option>
			    </select>
			  </div>
			  <div class="form-group">
			    <label for="pin">Pin</label>
			    <input type="text"  required class="form-control" id="pin" name="pin" placeholder="Pin">
			  </div>
			  <div class="form-group">
			    <label for="amount">Amount</label>
			    <input type="text" required class="form-control" id="amount" name="amount" placeholder="Pin">
			  </div>
			  <div class="form-group">
			    <label for="expirationDate">Expiration Date</label>
			    <input type="date" required  class="form-control" id="expirationDate" name="expirationDate" placeholder="Expiration Date">
			  </div>
			  <button type="submit" class="btn btn-primary">Submit</button>
			</form>
		    </div>
		    <div class="col-md-4"></div>
		 </div>
	</div>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>