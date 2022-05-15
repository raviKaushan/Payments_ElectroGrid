<%@page import="com.Payments"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Payments ElectroGrid</title>

<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/Payments.js"></script>

</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Payments</h1>
				
				<form id="formPayment" name="formPayment" method="post" action="Payments.jsp">


					Bill ID: <input id="b_id" name="b_id" type="text"
						class="form-control form-control-sm"> 
						
						<br>Account Number: <input id="account_number" name="account_number" type="text"
						class="form-control form-control-sm"> 
						
						<br> Customer ID: <input id="c_id" name="c_id" type="text"
						class="form-control form-control-sm"> 
						
						<br> Customer Name: <input id="c_name" name="c_name" type="text"
						class="form-control form-control-sm">
						
						<br> Total Amount: <input id="amount" name="amount" type="text"
						class="form-control form-control-sm">
						
						<br> Card Number: <input id="card_number" name="card_number" type="text"
						class="form-control form-control-sm">
						
						<br> Bank Name: <input id="bank_name" name="bank_name" type="text"
						class="form-control form-control-sm">
						
						<br> Card Exp Date: <input id="card_exp_date" name="card_exp_date" type="text"
						class="form-control form-control-sm">
						
						<br> CVV: <input id="cvv" name="cvv" type="text"
						class="form-control form-control-sm">
						
						<br> Payment Date: <input id="date" name="date" type="text"
						class="form-control form-control-sm">
						
						
						<br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidProjectIDSave" name="hidProjectIDSave" value="">
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divProjectGrid">
					<%
					Payments projectObj = new Payments();
						out.print(projectObj.readProject());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>