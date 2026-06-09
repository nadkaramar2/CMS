
$(document).ready(function() {
	var value = $("#addInstForm").validate({
		rules: {
			institutionCode: {
                    required: true,
                    minlength: 2,
                    maxlength: 10         
            },
            institutionDesc: {
                required: true,
                minlength: 4,
                maxlength: 20         
            }
		},
    	messages: {
    		institutionCode: {
                    required: "Please Enter Institution Code!",
                    minlength: "Minmum {0} characters required!",
                    maxlength: "Maximum {0} characters allowed!"
            },
            institutionDesc: {
                required: "Please Enter Institution Desc!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!"
            }
    	},
		submitHandler: function(form) {
			return true;
				form.submit();	
		}
	});
});