
$(document).ready(function() {
	var value = $("#loginForm").validate({
		rules: {
			username: {
                    required: true,
                    minlength: 4,
                    maxlength: 20         
            },
            password: {
                required: true,
                minlength: 4,
                maxlength: 20         
            },
            institutionID: {
                required: true                         
            }
		},
    	messages: {
    		username: {
                    required: "Please Enter User Name!",
                    minlength: "Minmum {0} characters required!",
                    maxlength: "Maximum {0} characters allowed!"
            },
            password: {
                required: "Please Enter Password!",
                minlength: "Minmum {0} characters required!",
                maxlength: "Maximum {0} characters allowed!"
            },
            institutionID: {
                required: "Please Select Participant!"    
            }
    	},
		submitHandler: function(form) 
		{
				var iterationCount = 1000;
				var keySize = 128;
				var plaintext = $('#password').val();
				var passphrase = 'K8%gsv*()';
				var iv = 'b0fd78db1b095b2cb02c249c3ff7ceb8';
				var salt = 'ef3ddaf43bb16823582c808d2a83c5c9';
				var aesUtil = new AesUtil(keySize, iterationCount);
				var ciphertext = aesUtil.encrypt(salt, iv, passphrase, plaintext);		
				form.password.value = ciphertext
				return true;
				form.submit();	
		}
	});
});