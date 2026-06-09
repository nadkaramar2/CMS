<script type="text/javascript">
$(document).ready(function() {
	
	var bulkMenuID = $('#leftbulkMenuID').val();
	
	switch (bulkMenuID) { 
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
		case '7': 
			$("#7").addClass("active");
			break;
		default:
			console.log("Invalid Menu ID !!!");
	}
});

</script>

<h3 class="page-title mb-5">Bulk Transfer</h3>
<div>
	<div class="list-group list-group-transparent mb-0">
		
		<a href="oneToManyAccounts" id="1"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span>One To Many
		</a> 
		
		<a href="manyToOneAccount" id="2"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span>Many To One
		</a> 
		
		<a href="glAccToManyAccounts" id="3"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span>GL To Many
		</a> 
		
		<a href="manyToGLAccount" id="4"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span>Many To GL
		</a> 		
		
		<a href="bulkTransferUpload" id="5"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span> Upload
		</a> 		
		
		<a href="pendingForVerification" id="6"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span> Verify
		</a> 
		
		<a href="authorizeForVerification" id="7"
			class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span> Authorize
		</a> 
	</div>
</div>