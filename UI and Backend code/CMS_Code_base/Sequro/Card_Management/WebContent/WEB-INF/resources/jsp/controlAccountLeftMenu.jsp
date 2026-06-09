<script type="text/javascript">
$(document).ready(function() {
	
	var cardMenuID = $('#leftControlAccountMenuID').val();
	
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

<h3 class="page-title mb-5">Control Account</h3>
<div>
	<div class="list-group list-group-transparent mb-0">
		<!-- <a href="controlAccount" id="1"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span>Create / View Control Account
		</a>  -->
		
		<a href="transferInOut" id="1"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span>Transfer IN / OUT
		</a> 
		
		
	</div>
</div>