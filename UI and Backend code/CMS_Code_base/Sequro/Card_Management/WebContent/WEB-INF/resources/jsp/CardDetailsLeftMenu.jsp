
<script type="text/javascript">
$(document).ready(function() {
	
	var cardMenuID = $('#leftCardMenuID').val();
	
	switch (cardMenuID) { 
		case '1': 
			 $("#1").addClass("active");
			break;
		case '2': 
			$("#2").addClass("active");
			break;
		case '3': 
			$("#3").addClass("active");
			break;		
		case '4': 
			$("#4").addClass("active");
			break;
		case '5': 
			$("#5").addClass("active");
			break;
		case '6': 
			$("#6").addClass("active");
			break;	
		default:
			console.log("Invalid Menu ID !!!");
	}
});
</script>

<h3 class="page-title mb-5">Card Details</h3>
<div>
	<div class="list-group list-group-transparent mb-0">
		<a href="cardDetails" id="1"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span>Limit
			Management
		</a> <a href="cardStatusForm" id="2"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Card
			Status Management
		</a> <a href="pinManagementForm" id="3"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fa fa-newspaper-o"></i></span>Pin
			Management
		</a> <a href="plasticManagementForm" id="4"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fa fa-address-book"></i></span>Plastic
			Management
		</a> <a href="serviceManagementForm" id="5"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-phone"></i></span>Service
			Management
		</a>
		<a href="mccManagementForm" id="6"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-list"></i></span>MCC
			Management
		</a>
		<div class="mt-6">
			<a href="cardDetailsForm" class="btn btn-secondary btn-block"><i class="fa fa-arrow-circle-left"></i>  Card Details</a>
		</div>
	</div>
</div>