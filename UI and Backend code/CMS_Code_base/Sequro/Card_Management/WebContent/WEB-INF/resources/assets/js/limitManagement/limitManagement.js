
	$(document).ready(function() {
		$('#submitBtn').click(function() {
			var status = false;
			var monthLimit = new Number($('#strMonthlyLimit').val());
			var weeklyLimit = new Number($('#strWeeklyLimit').val());
			var dailyLimit = new Number($('#strDailyLimit').val());
			
			
			if(weeklyLimit < dailyLimit) {
				$('#strDailyLimitError').text("Daily Limit Greater than Monthly Limit");
				status = false;
			}else {
				$('#strDailyLimitError').text("");
				status = true;
			}
			
			if(status) {
			if(monthLimit < weeklyLimit) {
				$('#strWeeklyLimitError').text("Weekly Limit Greater than Monthly Limit");
				status = false;
			}else {
				$('#strWeeklyLimitError').text("");
				status = true;
			}
			}
			return status;
			
		});
	});