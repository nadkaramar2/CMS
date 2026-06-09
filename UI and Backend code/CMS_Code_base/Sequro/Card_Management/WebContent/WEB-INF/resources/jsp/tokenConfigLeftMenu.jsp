
<script type="text/javascript">
$(document).ready(function() {
	
	var tokenMenuID = $('#lefttokenMenuID').val();
	//var cardMenuID = $('#leftCardMenuID').val(); 
	
	switch (tokenMenuID) { 
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
		default:
			console.log("Invalid Menu ID !!!");
	}
});
</script>

<h3 class="page-title mb-5">Token Configuration</h3>
<div>
	<div class="list-group list-group-transparent mb-0">
	
	
	<a href="addCardTokenConfigForm" id="1"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Card Token Configuration
		</a> 
	
		<a href="addMobileTokenConfigForm" id="2"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Mobile Token Configuration
		</a> 
		
	
		
	</div>
</div>