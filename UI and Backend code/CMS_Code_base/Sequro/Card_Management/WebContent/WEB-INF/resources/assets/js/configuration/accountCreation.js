$(document).ready(function() {
	
	$("#resetBtn").click(function(){
		var spans = $('.error');
		spans.text('');
		var messages = $('.messages');
		messages.text('');
		$("#accountCreation")[0].reset()
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
	
	var value = $("#accountCreation").validate({
		rules : {
								strTitle : {
									required : true
								},
								strCIFKey : {
									required : true,
									regex : /^[a-zA-Z0-9]+$/,
									minlength : 2,
									maxlength : 15
								},
								strFirstName : {
									required : true,
									regex : /^[a-zA-Z]+$/,
									minlength : 2,
									maxlength : 30
								},
								strMiddleName : {
									required : false,
									regex : /^[a-zA-Z]+$/

								},
								strLastName : {
									required : true,
									regex : /^[a-zA-Z]+$/,
									minlength : 2,
									maxlength : 30
								},
								strMobileNo : {
									required : true,
									regex : /^[0-9]+$/,
									minlength : 10,
									maxlength : 10
								},
								strGender : {
									required : true
								},
								strDOB : {
									required : true
								},
								strDocumentType : {
									required : true
								},
								strDocumentNumber : {
									required : true,
									regex : /^[a-zA-Z0-9]+$/,
									minlength : 8,
									maxlength : 20
								},
								strAddress1 : {
									required : true,
									regex : /^[a-zA-Z0-9\s]+$/,
									minlength : 2,
									maxlength : 40
								},
								/* strAddress2 : {
									required : true,
									regex : /^[a-zA-Z0-9\s]+$/,
									minlength : 2,
									maxlength : 40
								}, */
								strCountryCode : {
									required : true
								},
								
								strAddressType : {
									required : true
								},
								strMotherMaidenName : {
									required : true,
									regex : /^[a-zA-Z]+$/,
									minlength : 2,
									maxlength : 40
								},
								strEmailID : {
									required : true,
									email : true,
									minlength : 2,
									maxlength : 90
								},
								strPinCode : {
									required : true,
									regex : /^[0-9]+$/,
									minlength : 6,
									maxlength : 6
								},
							},
							messages : {
								strTitle : {
									required : "Please Select Title!"
								},
								strFirstName : {
									required : "Please Enter First Name!",
									minlength : "Minmum {0} characters required!",
									maxlength : "Maximum {0} characters allowed!",
									regex : "only Char allow !"
								},
								strMiddleName : {
									required : "Please Enter Middle Name!",
									regex : "only Char allow !"
								},
								strLastName : {
									required : "Please Enter Last Name!",
									minlength : "Minmum {0} characters required!",
									maxlength : "Maximum {0} characters allowed!",
									regex : "only Char allow !"
								},
								strMobileNo : {
									required : "Please Enter Mobile No!",
									minlength : "Minmum {0} characters required!",
									maxlength : "Maximum {10} characters allowed!",
									regex : "only Numeric allow !"
								},
								strGender : {
									required : "Required !"
								},
								strDOB : {
									required : "Required !"
								},
								strDocumentType : {
									required : "Required !"
								},
								strDocumentNumber : {
									required : "Please Enter Document Number!",
									minlength : "Minmum {0} characters required!",
									maxlength : "Maximum {0} characters allowed!",
									regex : "only Alphanumeric allow !"
								},
								strAddress1 : {
									required : "Please Enter Address1!",
									minlength : "Minmum {0} characters required!",
									maxlength : "Maximum {0} characters allowed!",
									regex : "only Alphanumeric allow !"
								},
								/* strAddress2 : {
									required : "Please Enter Address2!",
									minlength : "Minmum {0} characters required!",
									maxlength : "Maximum {0} characters allowed!",
									regex : "only Alphanumeric allow !"
								}, */
								strCountryCode : {
									required : "Required !"
								},
								strState : {
									required : "Required !"
								},
								strCity : {
									required : "Required !"
								},
								strAddressType : {
									required : "Required !"
								},
								strMotherMaidenName : {
									required : "Please Enter Mother Maiden Name!",
									minlength : "Minmum {0} characters required!",
									maxlength : "Maximum {0} characters allowed!",
									regex : "only Char allow !"
								},
								strPinCode : {
									required : "Please Enter Pin Code!",
									minlength : "Minmum {0} characters required!",
									maxlength : "Maximum {0} characters allowed!",
									regex : "only Numeric allow !"
								},
								strEmailID : {
									required : "Please Enter Email ID!",
									email : "Invalid Email",
									minlength : "Minmum {0} characters allowed!",
									maxlength : "Maximum {0} characters allowed!"
								}
							},
		submitHandler : function(form) {
			return true;
			form.submit();
		}
	});
});