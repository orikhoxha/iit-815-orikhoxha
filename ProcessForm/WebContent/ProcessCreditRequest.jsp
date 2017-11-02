<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.text.SimpleDateFormat" %>

<html>
<head>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
	
	<%
		HttpSession httpSession = request.getSession();
		String creditCardNumber = (String)httpSession.getAttribute("creditCardNumber");
		String creditCardType = (String)httpSession.getAttribute("creditCardType");
		String pin = (String)httpSession.getAttribute("pin"); 
		String amount = (String)httpSession.getAttribute("amount");
		String expirationDate = (String)httpSession.getAttribute("expirationDate");
	%>
	
	<div id="thank_you">	
		<h1>Thank you</h1>
		<h3>Data Entered</h3>
		<h5>Credit Card: <%= creditCardNumber%></h5>
		<h5>Credit Card Type: <%= creditCardType%></h5>
		<h5>Pin: <%= pin%></h5>
		<h5>Amount: <%= amount%></h5>
		<h5>Expiration Date:<%= expirationDate%></h5>
		<h5></h5>
	</div>
	
</body>
</html>





