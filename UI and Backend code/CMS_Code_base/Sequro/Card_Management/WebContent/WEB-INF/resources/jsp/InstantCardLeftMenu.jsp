
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
		default:
			console.log("Invalid Menu ID !!!");
	}
});
</script>

<h3 class="page-title mb-5">Instant Cards</h3>
<div>
	<div class="list-group list-group-transparent mb-0">
		<a href="addInstantCardsForm" id="1"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Instant Cards Generate
		</a> <a href="updateInstantCardsForm" id="2"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-credit-card"></i></span>Update Instant Cards
		</a> 
	
	</div>
</div>