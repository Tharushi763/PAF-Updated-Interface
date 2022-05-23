
<%@page import="com.Billing"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

        
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Billing Service Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<link rel="stylesheet" href="Views/Billing.css">
<script src="Components/jquery-3.2.1.js"></script>
<script src="Components/Billing.js"></script>

</head>
<body>

 <div class="container">
 <div class="row">
 <div class="col-6">

<h1><b>Billing Management</b></h1>
<form id="formBilling" name="formBilling" method="post" enctype="multipart/form-data">
    Customer Name:
	<input id="cName" name="cName" type="text" class="form-control form-control-sm">
	<br>  
	Customer Account Number:
	<input id="cAccNo" name="cAccNo" type="number" class="form-control form-control-sm">
	<br> 
	Billing Date:
	<input id="billingDate" name="billingDate" type="text" class="form-control form-control-sm">
	<br> 
    No. of Units:
    <input id="noOfUnitis" name="noOfUnitis" type="number" class="form-control form-control-sm">
    <br> 
    Unit Price:
    <input id="noOfUnitis" name="unitPrice" type="number" class="form-control form-control-sm">
	<br>
	<br>
	<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">
	<input type="hidden" id="hidBillingIDSave" name="hidBillingIDSave" value="">
</form>
<br>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>

<br>
<div id="divBillingGrid">
	 <%
	 	Billing billing = new Billing();
	 	out.print(billing.readBillings());
	 %>
</div>

</div>
</div>
</div>
</body>
</html>

    