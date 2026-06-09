
<script type="text/javascript">
$(document).ready(function()
{
	var customerAccountMenuID = $('#leftCustomerAccountMenuID').val();	
	switch (customerAccountMenuID) 
	{ 
		case '1': $("#1").addClass("active"); break;
			
		case '2': $("#2").addClass("active"); break;
			
		case '3': $("#3").addClass("active"); break;
			
		case '4': $("#4").addClass("active"); break;
			
		case '5': $("#5").addClass("active"); break;
			
		case '6': $("#6").addClass("active"); break;
			
		case '7': $("#7").addClass("active"); break;
		
		case '8': $("#8").addClass("active"); break;
	
		case '9': $("#9").addClass("active"); break;	
		
		default: console.log("Invalid Menu ID !!!");
	}
});
</script>

<h3 class="page-title mb-5">Customer / Account</h3>
<div>
	<div class="list-group list-group-transparent mb-0">
		
		<a href="customerIdCreationConfig" id="1"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Create Customer
		</a> 
		
	
		 <a href="viewCustomerAccountsDetails" id="2"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>View / Edit Customer
		</a> 
		
		<a href="approveUpgradeTier" id="3"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Approve Upgrade Tier
		</a>
		
		<a href="linkedPreAccountForm" id="4" style="display: none !important;"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Approve Customer
		</a> 
		
		<a href="accountCreation" id="5" 
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Create Account
		</a> 
		
		
		<a href="viewAccountCreationForm" id="6"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>View / Edit Account
		</a> 
		
		
		<a href="instantAccountCreation" id="7"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Create Instant Account
		</a> 
	
		<a href="viewInstantAccountForm" id="8"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>View / Edit Instant Account
		</a> 
		
		<a href="nonLinkedCustomerForAccountNo" id="9"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Approve Account
		</a>
				       
	</div>
</div>