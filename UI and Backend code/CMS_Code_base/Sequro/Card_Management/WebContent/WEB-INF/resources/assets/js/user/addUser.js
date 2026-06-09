$(document).ready(function() {
	
	$("#resetBtn").click(function(){
		var spans = $('.error');
		spans.text('');
		var messages = $('.messages');
		messages.text('');
		$("#addUserForm")[0].reset()
	});
	
	jQuery.validator.addMethod(
			  "regex",
			   function(value, element, regexp) {
			       if (regexp.constructor != RegExp)
			          regexp = new RegExp(regexp);
			       else if (regexp.global)
			          regexp.lastIndex = 0;
			          return this.optional(element) || regexp.test(value);
			   },"erreur expression reguliere"
			);
	
	var value = $("#addUserForm").validate({
		rules : {
			strUserName : {
				required : true,
				minlength : 2,
				maxlength : 15
			},
			strRoleID : {
				required : true
			},
			strFirstName : {
				required : true,
				minlength : 2,
				maxlength : 15
			},
			strLastName : {
				required : true,
				minlength : 2,
				maxlength : 15
			},
			strMobileNo : {
				required : true,
				regex: "[0-9]+",
				minlength : 2,
				maxlength : 10
			},
			strEmailID : {
				required : true,
				email : true,
				minlength : 4,
				maxlength : 70
			},
			strUserAccessType : {
				required : true
			},
			strSecQueID : {
				required : true
			},
			strSecQueAns : {
				required : true,
				minlength : 2,
				maxlength : 50
			}
		},
		messages : {
			strUserName : {
				required : "Please Enter User Name!",
				minlength : "Minmum {0} characters required!",
				maxlength : "Maximum {0} characters allowed!"
			},
			strMobileNo : {
				required : "Please Enter Mobile Number!",
				regex :   "Invalid Mobile Number",
				minlength : "Minmum {0} characters required!",
				maxlength : "Maximum {0} characters allowed!"
			},
			strRoleID : {
				required : "Please Select Role Name!"
			},
			strFirstName : {
				required : "Please Enter First Name!",
				minlength : "Minmum {0} characters required!",
				maxlength : "Maximum {0} characters allowed!"
			},
			strLastName : {
				required : "Please Enter Last Name!",
				minlength : "Minmum {0} characters required!",
				maxlength : "Maximum {0} characters allowed!"
			},
			strEmailID : {
				required : "Please Enter email address!",
				email : "Please enter a valid email address!",
				minlength : "Minmum {0} characters required!",
				maxlength : "Maximum {0} characters allowed!"
			},
			strUserAccessType : {
				required : "Please Select Role Name!"
			},
			strSecQueID : {
				required : "Please Select Role Name!"
			},
			strSecQueAns : {
				required : "Please Enter Answer!",
				minlength : "Minmum {0} characters required!",
				maxlength : "Maximum {0} characters allowed!"
			}
		},
		submitHandler : function(form) {
			return true;
			form.submit();
		}
	});
});