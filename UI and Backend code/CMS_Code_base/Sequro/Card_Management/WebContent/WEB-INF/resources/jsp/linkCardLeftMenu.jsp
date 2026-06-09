
<script type="text/javascript">
$(document).ready(function() 
{	
	var linkCardMenuID = $('#leftLinkCardMenuId').val();
	
	switch (linkCardMenuID) 
	{
		case '1': 
			 $("#1").addClass("active");
			break;	
		
		case '2': 
			 $("#2").addClass("active");
			break;
			
	
			
		default:
			console.log("Invalid Customer Menu ID !!!");
	}
});
</script>

<h3 class="page-title mb-5">Link Cards</h3>
<div>
	<div class="list-group list-group-transparent mb-0">
		
		<a href="cardAccountLinkage" id="1" class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span>Card-Account Linkage
		</a>
		<a href="cardAccountLinkageView" id="2" class="list-group-item list-group-item-action d-flex align-items-center">
			<span class="icon mr-3"><i class="fe fe-user"></i></span>View Linked Cards
		</a> 
		
		
		
	</div>
</div>