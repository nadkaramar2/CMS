
<script type="text/javascript">
$(document).ready(function() {
	
	var binCardMenuID = $('#leftbinCardMenuID').val();
	//var cardMenuID = $('#leftCardMenuID').val(); 
	
	switch (binCardMenuID) { 
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

<h3 class="page-title mb-5">Bin & Card Configuration</h3>
<div>
	<div class="list-group list-group-transparent mb-0">
	
	<!-- accountTypeMasterConfig -->
	
	<a href="binConfigForm" id="1"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Bin Configuration
		</a> 
	
		<a href="cardTypeConfigForm" id="2"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Card Type Configuration
		</a> 
		
		
		  <a href="cardTemplateConfigForm" id="3"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Card Template Configuration
		</a>
		
		 <a href="cardPlasticConfigForm" id="4"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Card Plastic Configuration
		</a> 
		<a href="addEmbossForm" id="5"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Emboss Configuration
		</a> 
		
		
		<!-- <a href="updateInstantAccountForm" id="3"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>View Available Instant Account
		</a>   -->
	</div>
</div>