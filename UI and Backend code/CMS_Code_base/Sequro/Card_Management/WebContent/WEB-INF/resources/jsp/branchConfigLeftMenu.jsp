
<script type="text/javascript">
$(document).ready(function() {
	
	var branchMenuID = $('#leftbranchMenuID').val();
	//var cardMenuID = $('#leftCardMenuID').val(); 
	
	switch (branchMenuID) { 
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

<h3 class="page-title mb-5">Branch Configuration</h3>
<div>
	<div class="list-group list-group-transparent mb-0">
	
	<!-- accountTypeMasterConfig -->
	
	<a href="branchConfigForm" id="1"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Branch Configuration
		</a> 
	
		<a href="branchCodeConfigForm" id="2"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Branch Code Configuration
		</a> 
		
		
		  <a href="accountConfigForm" id="3"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Account Type Configuration
		</a>
		
		 <a href="addressConfigForm" id="4"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Address Type Configuration
		</a>
		
		 <a href="emailConfigForm" id="5"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Email Type Configuration
		</a>
		
		
	</div>
</div>