<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
	HttpSession httpSession = request.getSession();
	if(!isNum(request.getParameter("creditCardNumber")) 
			|| !isNum(request.getParameter("pin")) || 
			!isNum(request.getParameter("amount"))){
				httpSession.setAttribute("charError", "No chars allowed for credit card num, pin, amount");
			response.sendRedirect("GetFormData.jsp");
	}else{
		String creditCardNumber = request.getParameter("creditCardNumber").trim();
		int creditCardType = Integer.parseInt(request.getParameter("creditCardType").trim());
		String pin = request.getParameter("pin").trim(); 
		int amount = Integer.parseInt(request.getParameter("amount").trim());
		String expirationDate = request.getParameter("expirationDate").trim();
		
		boolean error = false;
		String blankError = "";
		String creditCardError = "";
		String pinError = "";
		String amountError = "";
		String dateError  = "";
	
		
		if(!validateCreditCard(creditCardType,creditCardNumber)){
			creditCardError += "Card Type error ";
			error = true;
			System.out.println("Error in validateCreditCard:" + error);
		}
		
		if(!validatePin(pin)){
			pinError += "Pin error";
			error = true;
			System.out.println("Error in validatePin:" + error);
		}
		
		if(validateAmount(amount)){
			amountError += "Amount error";
			error = true;
			System.out.println("Error in validateAmount:" + error);
		}
		
		if(!validateDate(expirationDate)){
			dateError += "Date error";
			error = true;
			System.out.println("Error in expirationDate:" + error);
		}
		
		if(error){
			System.out.println("Error:" + error);
			httpSession.setAttribute("blankError",blankError);
			httpSession.setAttribute("creditCardError",creditCardError);
			httpSession.setAttribute("pinError",pinError);
			httpSession.setAttribute("amountError",amountError);
			httpSession.setAttribute("dateError",dateError);
			response.sendRedirect("GetFormData.jsp");
		}else{
			httpSession.setAttribute("creditCardNumber","" +creditCardNumber);
			httpSession.setAttribute("creditCardType", "" + creditCardType);
			httpSession.setAttribute("pin", "" + pin);
			httpSession.setAttribute("amount", "" + amount);
			httpSession.setAttribute("expirationDate", "" + expirationDate);
			response.sendRedirect("ProcessCreditRequest.jsp");
		}
	}
	
%>

<!-- Validate  -->
<%! 

    private boolean validateCreditCard(int creditCardType, String creditCardNumber){
		
		if(!isNum(creditCardNumber)){
			System.out.println("Is not number");
			return false;
		}
	
		if(validateCreditCardType(creditCardType, creditCardNumber) && validateCreditCardNumber(creditCardNumber)){
			System.out.println("Could validate");
			return true;
		}
		
		System.out.println("Could not validate");
		return false;
	}

	private boolean validateCreditCardType(int creditCardType,String creditCardNumber){
		
		switch(creditCardType){
		case 1:
			if(creditCardNumber.length() == 13 || creditCardNumber.length() != 16){
				System.out.println("Length:" + creditCardNumber.length());
				return true;
			}
		case 2:
			if(creditCardNumber.length() == 16){
				System.out.println("Length:" + creditCardNumber.length());
				System.out.println("Length not good");
				return true;
			}
		case 3:
			if(creditCardNumber.length() == 15){
				System.out.println("Length:" + creditCardNumber.length());
				System.out.println("Length not good");
				return true;
			}
		case 4:
			
			if(creditCardNumber.length() == 14 || creditCardNumber.length() == 15 || creditCardNumber.length() == 16){
				System.out.println("Length:" + creditCardNumber.length());
				System.out.println("Length not good");
				return true;
			}
		}
		return false;
	}

	private static  boolean validateCreditCardNumber(String creditCardNumber){
			
			String[] numsString = creditCardNumber.split("");
			List<Integer> numsOdd = new ArrayList<Integer>();
			List<Integer> numsEven = new ArrayList<Integer>();
			int sumEven = 0;
			int sumOdd = 0;
			
			for(int i = numsString.length - 1; i >= 0;i -= 2){
				numsOdd.add(Integer.parseInt(numsString[i]));
				sumOdd += numsOdd.get(numsOdd.size() - 1);
			}
	
			for(int i = numsString.length - 2; i >= 0;i -= 2){
				numsEven.add(Integer.parseInt(numsString[i]) * 2);
			}
			
			for(int i = 0; i < numsEven.size();i++){
				if(numsEven.get(i) >= 10){
					int a = numsEven.get(i) / 10;
					int b = numsEven.get(i) % 10;
					numsEven.set(i,a + b);
				}
				sumEven += numsEven.get(i);
				
			}	
		
			System.out.println("good job card: " + ((sumEven + sumOdd) % 10 == 0));
			return (sumEven + sumOdd) % 10 == 0;
	}
	
	private static boolean validatePin(String num){
		
		if(!isNum(num)){
			System.out.println("Could not validate number bad");
			return false;
		}

		if(num.length() > 4 || num.length() <= 0){
			return false;
		}

		String[] nums = num.split("");

		for(int i = 1; i < nums.length;i++){
			if(Integer.parseInt(nums[0]) != Integer.parseInt(nums[i])){
				return true;
			}
		}
		return false;
	}
	
	private static boolean validateAmount(int amount){
		if(amount > 500 || amount < 10){
			return true;
		}
		
		System.out.println("returning false from validateAmount()");
		return false;
	}
	
	private static boolean validateDate(String date){
		Date today = new Date();
		try{
			Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			return d.after(today);			
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}

	}
	
	public static boolean isNum(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}

%>

