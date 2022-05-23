$(document).ready(function()
{
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

/*
//SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateUnitForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidUnitIDSave").val() == "") ? "POST" : "PUT";
	var formData = new FormData($("#formUnits")[0]);
	console.log(formData);
	$.ajax({
		url : "UnitsAPI",
		type : type,
		data :formData,
		dataType : "text",
		complete : function(response, status) {
			onUnitSaveComplete(response.responseText, status);
		}
	});
});

function onUnitSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divUnitsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}

	$("#hidUnitIDSave").val("");
	$("#formUnits")[0].reset();
}

*/
//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------
	 $("#alertSuccess").text("");
	 $("#alertSuccess").hide();
	 $("#alertError").text("");
	 $("#alertError").hide(); 
	 
	// Form validation-------------------
	 var status = validateBillingForm();
	 if (status != true)
		 {
		  $("#alertError").text(status);
		  $("#alertError").show();
		  return;
	 }
	 //If status equals to true
	 var type = ($("#hidBillingIDSave").val() == "") ? "POST" : "PUT";
	 var formData = new FormData($("#formBillings")[0]);
	 console.log(formData);
	 $.ajax(
	 {
		 url : "BillingAPI",
		 type : type,
		 data : formData,
		 enctype : "multipart/form-data",
		 complete : function(response, status)
		 {
			 onBillingSaveComplete(response.responseText, status);
		 },
		 processData : false,
		 contentType :false
	 }); 
});




function onBillingSaveComplete(response, status)
{
	if (status == "success")
	 {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divBillingsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		}
	 } else if (status == "error")
	 {
			 $("#alertError").text("Error while saving.");
			 $("#alertError").show();
	 } else
	 {
			 $("#alertError").text("Unknown error while saving..");
			 $("#alertError").show();
	 } 
	
	 $("#hidBillingIDSave").val("");
	 $("#formBillings")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	 $("#hidBillingIDSave").val($(this).data("cid")); 
	 $("#cName").val($(this).closest("tr").find('td:eq(0)').text());
	 $("#cAccNo").val($(this).closest("tr").find('td:eq(1)').text());
	 $("#billingDate").val($(this).closest("tr").find("td:eq(2)").text());
	 $("#noOfUnitis").val($(this).closest("tr").find('td:eq(3)').text());
	 $("#unitPrice").val($(this).closest("tr").find('td:eq(4)').text());
});

//client-model
function validateBillingForm()
{
	// Customer Name
	if ($("#cName").val().trim() == "")
	{
		return "Insert Customer Name.";
	}
	// Customer AccountName
	 var tmpAccNum = $("#cAccNo").val().trim();
	 if (!$.isNumeric(tmpAccNum)) 
	{
		return "Insert Account Number.";
	}
	// Billing Area
	if ($("#billingDate").val() == "0")
	{
		return "Insert Billing Date.";
	}
	// Customer Phonr Number
	 var tmpPhone = $("#noOfUnitis").val().trim();
	 if (!$.isNumeric(tmpPhone)) 
	 {
		 return "Insert No of Units.";
	 }
	 //Billing
	 if ($("#unitPrice").val() == "0")
	 {
		return "Insert Unit Price.";
	 }
	
	return true;
}

$(document).on("click", ".btnRemove", function(event)
{
		$.ajax(
		 {
			 url : "BillingsAPI",
			 type : "DELETE",
			 data : "cID=" + $(this).data("cid"),
			 dataType : "text",
			 complete : function(response, status)
			 {
				 onBillingDeleteComplete(response.responseText, status);
			 }
		 });
});


function  onBillingDeleteComplete(response, status)
{
	if (status == "success")
	 {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success")
		{
			 $("#alertSuccess").text("Successfully deleted.");
			 $("#alertSuccess").show();
			 $("#divBillingsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error")
		{
			 $("#alertError").text(resultSet.data);
			 $("#alertError").show();
		}
	 } else if (status == "error")
	 {
			 $("#alertError").text("Error while deleting.");
			 $("#alertError").show();
	 } else
	 {
			 $("#alertError").text("Unknown error while deleting..");
			 $("#alertError").show();
	 }
}


