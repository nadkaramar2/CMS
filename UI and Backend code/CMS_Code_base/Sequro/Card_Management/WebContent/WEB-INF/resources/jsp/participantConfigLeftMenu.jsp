
<script type="text/javascript">
$(document).ready(function() {
	
	var partipantMenuID = $('#leftpartipantMenuID').val();
	//var cardMenuID = $('#leftCardMenuID').val(); 
	
	switch (partipantMenuID) { 
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

<h3 class="page-title mb-5">Participant Configuration</h3>
<div>
	<div class="list-group list-group-transparent mb-0">
	
	
	<a href="partConfigForm" id="1"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Participant Configuration
		</a> 
	
		<a href="connConfigForm" id="2"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Connection Configuration
		</a> 
		
		
		  <a href="isoConfigForm" id="3"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>ISO Configuration
		</a>
		
	</div>
</div>