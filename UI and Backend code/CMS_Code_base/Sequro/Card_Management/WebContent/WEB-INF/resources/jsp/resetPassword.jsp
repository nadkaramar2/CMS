<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<style>
.field-icon {
  float: right;
  margin-left: -25px;
  margin-top: -25px;
  position: relative;
  z-index: 2;
}
</style>
<script type="text/javascript">

$(document).ready(function()
	{
	$.validator.addMethod("regex", function(value, element, regexp) {
		var check = false;
		return this.optional(element) || regexp.test(value);
	}, "ss");

	var value = $("#resetPassword").validate({
		rules : {
			oldpassword : {
				required : true,
				minlength : 8,
				maxlength : 20
			},
			newpassword : {
				required : true,
				regex: /^\S*$/,
				minlength : 8,
				maxlength : 20
			},
			confirmnewpassword : 
			{
				required : true,
				regex: /^\S*$/,
				minlength : 8,
				maxlength : 20
			}
		},
		messages : {
			oldpassword : 
			{
				required : "Please Enter Old Password!",
				minlength : "Minmum {8} characters required!",
				maxlength : "Maximum {0} characters allowed!"
			},
			newpassword : 
			{
				required : "Please Enter New Password!",
				minlength : "Minmum {8} characters required!",
				maxlength : "Maximum {20} characters allowed!",
				regex: "Password must to be at least 8 Chars, 1 Number, 1 upper case, 1Lower Case, 1 SpecialChar, No Spaces !"
			},
			confirmnewpassword : 
			{
				required : "Please Enter Confirm New Password!",
				minlength : "Minmum {8} characters required!",
				maxlength : "Maximum {20} characters allowed!",
				regex: "Password must to be at least 8 Chars, 1 Number, 1 upper case, 1Lower Case, 1 SpecialChar, No Spaces !"
			}
		},
		submitHandler : function(form) 
		{
			form.submit();
		}
	});
	
	$(".toggle-password").click(function() 
	{
		  $(this).toggleClass("fa-eye fa-eye-slash");
		  var input = $($(this).attr("toggle"));
		  if (input.attr("type") == "password") 
		  {
		    input.attr("type", "text");
		  } 
		  else 
		  {
		    input.attr("type", "password");
		  }
	});
	
});


</script>

<body class="">
	<div class="page">
		<div class="page-single">
			<div class="container">
				<div class="row">
					<div class="col col-login mx-auto">

						<form:form method="post" cssClass="card"
							commandName="resetPassword"
							action="${pageContext.request.contextPath}/resetPassword"
							name="resetPassword" id="resetPassword">
							<div class="card-body p-6">

								<div class="text-center">
									<%-- <img
										src="${pageContext.request.contextPath}/resources/assets/images/logo_sequro.jpg"
										width="60%" height="50%" alt=""> --%>
										<img
										src="${pageContext.request.contextPath}/resources/assets/images/logo.jpg"
										width="60%" height="50%" alt="">
								</div>
								<div class="card-title">Reset Your Password</div>
								<c:if test="${not empty error}">
									<div class="error">${error}</div>
								</c:if>
								<c:if test="${not empty msg}">
									<div class="error">${msg}</div>
								</c:if>
								<div class="form-group">
									<label class="form-label">Old Password *</label> <input
										type="text" class="form-control" id="oldpassword"
										name="oldpassword" aria-describedby="emailHelp"
										placeholder="Old Password">
								</div>
								<div class="form-group">
									<label class="form-label">New Password * </label> <input
										type="password" class="form-control" id="newpassword"
										placeholder="New Password" name="newpassword">
										<span toggle="#newpassword" class="fa fa-fw fa-eye field-icon toggle-password"></span>
										
								</div>
								<div class="form-group">
									<label class="form-label">Confirm New Password * </label> 
									<input type="password" class="form-control" id="confirmnewpassword"
										placeholder="Confirm New Password" name="confirmnewpassword">
										<span toggle="#confirmnewpassword" class="fa fa-fw fa-eye field-icon toggle-password"></span>
										
								</div>

								<div class="form-footer">
									<button type="submit" class="btn btn-primary btn-block">Submit</button>
								</div>
							</div>
						</form:form>
						<!-- <div class="text-center text-muted">
							Don't have account yet? <a href="./register.html">Sign up</a>
						</div> -->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>