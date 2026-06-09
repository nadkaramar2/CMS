
$(document).ready(function() {
	var value = $("#resetPassword").validate({
		rules: {
			oldpassword: {
				required: true,
				minlength: 4,
				maxlength: 20
			},
			newpassword: {
				required: true,
				minlength: 4,
				maxlength: 20
			},
			confirmnewpassword: {
				required: true,
				minlength: 4,
				maxlength: 20
			}
		},
		messages: {
			oldpassword: {
				required: "Please Enter Old Password!",
				minlength: "Minmum {0} characters required!",
				maxlength: "Maximum {0} characters allowed!"
			},
			newpassword: {
				required: "Please Enter New Password!",
				minlength: "Minmum {0} characters required!",
				maxlength: "Maximum {0} characters allowed!"
			},
			confirmnewpassword: {
				required: "Please Enter Confirm New Password!",
				minlength: "Minmum {0} characters required!",
				maxlength: "Maximum {0} characters allowed!"
			}
		},
		submitHandler: function(form) 
		{
				form.submit();	
		}
	});
});