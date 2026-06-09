<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

var data;

$(document).ready(function() 
{
	
	$('#startLogging').click(function(event) 
	{
	    event.preventDefault(); // Prevent the default action of the click event
	    console.log("Inside Start Logging");
	  
	    var userid = $('#userid').val();
	    console.log("userid"+userid);
	    var dataToSend = {
	    		userid: userid
	        };
	    
	    console.log("DataToSend:::::"+dataToSend);
	    
	    $.ajax({
	        type: "POST",
	        contentType: "application/json",
	        url: "${pageContext.request.contextPath}/startLogging",
	        data: JSON.stringify(),
	        dataType: 'json',
	        timeout: 100000,
	        success: function(data) 
	        {
	            console.log("SUCCESS: ", data.responseDesc);
	            if(data.responseCode == "00") 
	            {
	                $('#LoggingError').addClass("tag tag-green");
	                $('#LoggingError').text(data.message);
	            }
	            else 
	            {
	                $('#LoggingError').addClass("tag tag-red");
	                $('#LoggingError').text(data.responseDesc);
	            }
	        },
	        error: function(e) {
	            console.log("ERROR: ", e);
	            $('#LoggingError').addClass("tag tag-red");
	            $('#LoggingError').text("Error");
	        },
	        done: function(e) {
	            console.log("DONE");
	        }
	    });
	    return false;
	})

		
		
		$('#stopLogging').click(function(event) 
		{
			event.preventDefault(); // Prevent the default action of the click event
		    console.log("Inside Start Logging");
		  
		    var userid = $('#userid').val();
		    console.log("userid"+userid);
		    var dataToSend = {
		    		userid: userid
		        };
		    
		    console.log("DataToSend:::::"+dataToSend);
		  
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "${pageContext.request.contextPath}/stopLogging",
				data: JSON.stringify(),
				dataType : 'json',
				timeout : 100000,
				success : function(data) 
				{
					console.log("SUCCESS: ", data.responseDesc);
					if(data.responseCode == "00")
					{
						$('#LoggingError').addClass("tag tag-green");
						$('#LoggingError').text(data.message);
					}
					else 
					{
						$('#LoggingError').addClass("tag tag-red");
						$('#LoggingError').text(data.responseDesc);
					}
				},
				error : function(e) {
					console.log("ERROR: ", e);
					$('#LoggingError').addClass("tag tag-red");
					$('#LoggingError').text("Error");
				},
				done : function(e) {
					console.log("DONE");
				}
			});
			
			return false;
		})
	});
	
</script>

<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<form:form action="startStopLogging" method="GET"
					commandName="userLoggingModel" name="userLoggingModel"
					id="userLoggingModel" cssClass="card">
					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;Logging Configuration
							<span class="text-right badge badge-danger" id="LoggingError"></span>
						</h3>
						<div class="text-right" style="display: ${display};" id="errorMsg">
							<c:if test="${not empty exception}">
								<div class="text-right badge badge-danger"
									style="display: ${display};">
									<c:out value="${exception}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty success}">
								<div class="text-right badge badge-success"
									style="display: ${display};">
									<c:out value="${success}"></c:out>
								</div>
							</c:if>
							<c:if test="${not empty error}">
								<div class="text-right badge badge-danger"
									style="display: ${display};">
									<c:out value="${error}"></c:out>
								</div>
							</c:if>
						</div>
					</div>
					<div class="modal-header">
						<div class="btn-list text-left">
							<button type="submit" id="startLogging" class="btn btn-primary">Start Logging</button>
						</div>
					</div>

					<div class="modal-header">
						<div class="btn-list text-right">
							<button type="submit" id="stopLogging"
								value="2" class="btn btn-primary">Stop Logging</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>