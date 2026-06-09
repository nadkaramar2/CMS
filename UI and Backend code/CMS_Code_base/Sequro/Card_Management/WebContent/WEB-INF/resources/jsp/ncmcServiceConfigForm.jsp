<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
.custom-header1 {
    text-align: center;
    padding: 3px;
    background: #1E90FF;
    color: #fff;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		
		$('#pre-selected-options').multiSelect({
			  selectableHeader: "<div class='custom-header1'>Service List</div>",
			  selectionHeader: "<div class='custom-header1'>Service Assigned To Card</div>",
			  selectableFooter: "<div class='custom-header1'></div>",
			  selectionFooter: "<div class='custom-header1'></div>"
		});
		 
		 
		var value = $("#ncmcConfig").validate({
			rules: {
				strCardType: {
	                    required: true
	            }
			},
	    	messages: {
	    		strCardType: {
	                    required: "Please Configure Card Type for NCMC!"
	            }
	    	},
			submitHandler: function(form) {
							form.submit();	
			}
		});
		
		$('#strCardType').change(function() {
			setTimeout(function(){// wait for 5 secs(2)
				var obj = { Title: '', Url: 'addNcmcConfigForm?strCardType='+$('#strCardType').val() };
		        history.pushState(obj, obj.Title, obj.Url);	
		           location.reload(); // then reload the page.(3)
		      }, 1); 
		})
		
		//ncmcListUpdate($('#strCardType').val()); 
		
		$('#submitBtn').click(function() {
			$('#NCMCServiceList').val($('#pre-selected-options').val());
		})
	});
	
	function ncmcListUpdate(type) {
		jsonObj = [];
		item = {}
        item ["strCardType"] = type;
        jsonObj.push(item);
        
        $.ajax({
			type : "POST",
			contentType : "application/json",
			url : "${pageContext.request.contextPath}/getNCMCListType",
			data: JSON.stringify(jsonObj).replace("[","").replace("]",""),
			dataType : 'json',
			timeout : 100000,
			success : function(data) {
				console.log("SUCCESS: ", data);
				
				 $.each(data, function( index, value ) {
					 if(value.strSelectID == "selected") {
						 $('#pre-selected-options')
				         .append($("<option></option>")
				         .attr("value",value.strSelectID)
				         .text(value.strSelectID));
					 }
					//$('#pre-selected-options').append('<option value="value.strNcmcID" value.strSelectID>value.strNcmcID</option>');
				}); 
			},
			error : function(e) {
				console.log("ERROR: ", e);
			},
			done : function(e) {
				console.log("DONE");
			}
		});
        return false;
	}

</script>


<div class="my-3 my-md-5">
	<div class="container">
		<div class="row">
			<div class="col-12">

				<form:form action="addNcmcConfig" method="post"
					commandName="ncmcServiceModel" name="ncmcConfig" id="ncmcConfig"
					cssClass="card">

					<div class="card-header">
						<h3 class="card-title">
							<i class="fe fe-user-plus"></i> &nbsp;&nbsp;NCMC Service
							Configuration <span class="text-right badge badge-danger"
								id="strError"></span>
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
					<div class="card-body">
						<div class="row">

							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<label class="form-label">Card Type <b>*</b></label>
									<form:select path="strCardType" id="strCardType"
										cssClass="form-control selectpicker">
										<form:option value=""></form:option>
										<c:forEach items="${cardTypeList}" var="itr">
											<form:option value="${itr.strID}">${itr.strCardType} : ${itr.strCardDesc}</form:option>
										</c:forEach>
									</form:select>
									<span class="error" id="strCardTypeError"><form:errors
											path="strCardType"></form:errors></span>
								</div>
							</div>
							
							<br>
							<div class="col-md-6 col-lg-6">
								<div class="form-group">
									<select id='pre-selected-options' name="pre-selected-options" multiple='multiple'>
										<c:forEach var="itr" items="${ncmcList}">
											<option value="${itr.strNcmcID}" ${itr.strSelectID}>${itr.strNcmcID}</option>
										</c:forEach>
									</select>
								</div>
							</div>

						</div>
					</div>
					<input type="hidden" value="" name="strNcmcID" id="NCMCServiceList">
					<div class="card-footer text-right">
						<div class=".d-flex1">
							<button type="submit" id="submitBtn"
								class="btn btn-primary ml-auto">Submit</button>
						</div>
					</div>


					<form:hidden path="strSelectID" id="strSelectID" />

				</form:form>
			</div>
		</div>
	</div>
</div>
