
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
		default:
			console.log("Invalid Customer Menu ID !!!");
	}
});
</script>

<h3 class="page-title mb-5">Customer Account View</h3>
<div>
	<div class="list-group list-group-transparent mb-0">
		<a href="viewAccountDetailsForm" id="1" class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span>Account Details
		</a> 
		<a href="viewCustomerCardDetails" id="2" class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Card Linked to Account
		</a> 
		
		<!-- added by sagark for linked account wallet date:21-12-2022 -->
		<a href="linkedAccountWallet" id="3" class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Linked Account Wallet
		</a>
	
	     <a href="viewAccountStatementForm" id="4" class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Account Statement
		</a>
		
	</div>
</div>