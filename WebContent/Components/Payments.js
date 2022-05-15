$(document).ready(function() {
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

// SAVE ============================================
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------
	var status = validateProjectForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------
	var type = ($("#hidProjectIDSave").val() == "") ? "POST" : "PUT";

	$.ajax({
		url : "PaymentsAPI",
		type : type,
		data : $("#formPayment").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onProjectSaveComplete(response.responseText, status);
		}
	});
});

function onProjectSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();

			$("#divProjectGrid").html(resultSet.data);
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

	$("#hidProjectIDSave").val("");
	$("#formPayment")[0].reset();
}

// UPDATE==========================================
$(document).on(
		"click",
		".btnUpdate",
		function(event) {
			$("#hidProjectIDSave").val(
					$(this).closest("tr").find('#hidProjectIDUpdate').val());
			
			$("#b_id").val($(this).closest("tr").find('td:eq(0)').text());
			$("#account_number").val($(this).closest("tr").find('td:eq(1)').text());
			$("#c_id").val($(this).closest("tr").find('td:eq(2)').text());
			$("#c_name").val($(this).closest("tr").find('td:eq(3)').text());
			$("#amount").val($(this).closest("tr").find('td:eq(4)').text());
			$("#card_number").val($(this).closest("tr").find('td:eq(5)').text());
			$("#bank_name").val($(this).closest("tr").find('td:eq(6)').text());
			$("#card_exp_date").val($(this).closest("tr").find('td:eq(7)').text());
			$("#cvv").val($(this).closest("tr").find('td:eq(8)').text());
			$("#date").val($(this).closest("tr").find('td:eq(9)').text());
			
			
		});

// REMOVE===========================================
$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "PaymentsAPI",
		type : "DELETE",
		data : "id=" + $(this).data("comid"),
		dataType : "text",
		complete : function(response, status) {
			onProjectDeleteComplete(response.responseText, status);
		}
	});
});

function onProjectDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);

		if (resultSet.status.trim() == "success") {

			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();

			$("#divProjectGrid").html(resultSet.data);

		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}

	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENT-MODEL=========================================================================
function validateProjectForm() {
	
	
	
	if ($("#b_id").val().trim() == "") {
		return "Insert Bill ID";
	}

	
	if ($("#account_number").val().trim() == "") {
		return "Insert Account Number";
	}
	
	
	 
	if ($("#c_id").val().trim() == "") {
		return "Insert Customer ID";
	}
	
	if ($("#c_name").val().trim() == "") {
		return "Insert Customer Name";
	}

	
	if ($("#amount").val().trim() == "") {
		return "Insert Bill Amount";
	}
	
	 
	if ($("#card_number").val().trim() == "") {
		return "Insert Card Number";
	}
	
	if ($("#bank_name").val().trim() == "") {
		return "Insert Bank Name";
	}

	
	if ($("#card_exp_date").val().trim() == "") {
		return "Insert Card Exp Date";
	}
	
	 
	if ($("#cvv").val().trim() == "") {
		return "Insert CVV";
	}
	
	if ($("#date").val().trim() == "") {
		return "Insert Payment Date";
	}
	
	 

	return true;
}