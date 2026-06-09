
<script type="text/javascript">
$(document).ready(function() {
	
	var cardMenuID = $('#leftCustomerMenuID').val();
	
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
			console.log("Invalid Customer Menu ID !!!");
	}
});
</script>

<h3 class="page-title mb-5">Customer Service</h3>
<div>
	<div class="list-group list-group-transparent mb-0">
		<a href="clientDetailsForm" id="1"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span>Customer
			Details
		</a> <a href="cardDetailsForm" id="2"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Card
			Details
		</a> <a href="accountDetailsForm" id="3"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fa fa-newspaper-o"></i></span>Account
			Details
		</a> <a href="addressDetailsForm" id="4"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fa fa-address-book"></i></span>Address
			Details
		</a> <a href="contactDetailsForm" id="5"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-phone"></i></span>Contact
			Details
		</a>
		<a href="documentDetailsForm" id="6"
				class="list-group-item list-group-item-action d-flex align-items-center">
				<span class="icon mr-3"><i class="fe fe-file-text"></i></span>Document Details
		</a>
	</div>
</div>