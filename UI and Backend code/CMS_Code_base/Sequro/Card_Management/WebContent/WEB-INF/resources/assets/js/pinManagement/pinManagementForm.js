$(document).ready(
			function() {

				var flag = $("#pinFlagTemp").val();

				if (flag == 'Y') {
					$('#pinFlag').attr("checked", true);
				} else {
					$('#pinFlag').attr("checked", false);
				}

				$("#pinMailerUpdateFlag").val("N");
				$("#pinRetryFlag").val("N");

				var strDailyPinRetryLimit = $('#strDailyPinRetryLimit').val();
				var strConsecutivePinRetryLimit = $(
						'#strConsecutivePinRetryLimit').val();

				$('#submitBtn').click(
						function() {
							if (strDailyPinRetryLimit != $(
									'#strDailyPinRetryLimit').val()
									|| strConsecutivePinRetryLimit != $(
											'#strConsecutivePinRetryLimit')
											.val()) {
								$("#pinRetryFlag").val("Y");
							}
						});

				$("#pinFlag").change(function() {
					if (this.checked) {
						$("#pinFlagTemp").val("Y");
					} else {
						$("#pinFlagTemp").val("N");
					}
					$("#pinMailerUpdateFlag").val("Y");
				});
			});