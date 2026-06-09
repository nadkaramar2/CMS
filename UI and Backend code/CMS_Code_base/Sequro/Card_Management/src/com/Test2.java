/*
 * package com;
 * 
 * import java.text.SimpleDateFormat; import java.time.Duration; import
 * java.time.LocalDateTime; import java.util.Date; import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * import com.traneco.login.model.UserLoginLog; import
 * com.traneco.user.services.UserService;
 * 
 * public class Test2 {
 * 
 * @Autowired UserService userService;
 * 
 * public static void main(String[] args) { UserLoginLog user = new
 * UserLoginLog(); SimpleDateFormat sdf = new
 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); List<UserLoginLog> usrLoginLog =
 * userService.getLastThreeFailedAttempts(user); try { List<LocalDateTime> dates
 * = null; Date date1 = sdf.parse("2023-12-30 17:57:19"); Date date2 =
 * sdf.parse("2023-12-30 17:55:50"); Date date3 =
 * sdf.parse("2023-12-30 17:55:35"); } catch (Exception e) {
 * e.printStackTrace(); }
 * 
 * 
 * 
 * 
 * for (int i = 0; i < dates.size() - 1; i++) { LocalDateTime date1 =
 * dates.get(i); LocalDateTime date2 = dates.get(i + 1);
 * 
 * Duration duration = Duration.between(date1, date2); long diffInMinutes =
 * duration.toMinutes();
 * 
 * if (diffInMinutes <= 10) { System.out.println("The dates " + date1 + " and "
 * + date2 + " are within 10 minutes of each other."); } else {
 * System.out.println("The dates " + date1 + " and " + date2 +
 * " are not within 10 minutes of each other."); } }
 * 
 * } }
 */