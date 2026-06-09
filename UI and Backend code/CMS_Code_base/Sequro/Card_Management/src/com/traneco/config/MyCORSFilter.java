/*
 * package com.traneco.config;
 * 
 * import java.io.IOException; import java.util.Arrays; import java.util.List;
 * 
 * import javax.servlet.Filter; import javax.servlet.FilterChain; import
 * javax.servlet.FilterConfig; import javax.servlet.ServletException; import
 * javax.servlet.ServletRequest; import javax.servlet.ServletResponse; import
 * javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.core.Ordered; import
 * org.springframework.core.annotation.Order; import
 * org.springframework.stereotype.Component; import
 * org.springframework.web.cors.CorsConfiguration; import
 * org.springframework.web.cors.CorsConfigurationSource; import
 * org.springframework.web.cors.UrlBasedCorsConfigurationSource;
 * 
 * @Component
 * 
 * @Order(Ordered.HIGHEST_PRECEDENCE) public class MyCORSFilter implements
 * Filter {
 * 
 * @Configuration public class CorsConfig {
 * 
 * @Bean public CorsConfigurationSource corsConfigurationSource() {
 * CorsConfiguration configuration = new CorsConfiguration();
 * configuration.setAllowedOrigins(Arrays.asList("http://103.11.153.212:8080"));
 * configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT",
 * "DELETE")); configuration.setAllowedHeaders(Arrays.asList("*"));
 * UrlBasedCorsConfigurationSource source = new
 * UrlBasedCorsConfigurationSource(); source.registerCorsConfiguration("/**",
 * configuration); return source; } }
 * 
 * private List<String> allowedOrigins =
 * Arrays.asList("http://103.11.153.212:8080", "http://103.11.153.212:8080");
 * 
 * @Override public void doFilter(ServletRequest req, ServletResponse res,
 * FilterChain chain) throws IOException, ServletException { HttpServletRequest
 * request = (HttpServletRequest) req; HttpServletResponse response =
 * (HttpServletResponse) res;
 * 
 * String origin = request.getHeader("Origin"); if
 * (allowedOrigins.contains(origin)) {
 * response.setHeader("Access-Control-Allow-Origin", origin); }
 * response.setHeader("Access-Control-Allow-Credentials", "true");
 * response.setHeader("Access-Control-Allow-Methods",
 * "POST, GET, OPTIONS, DELETE"); response.setHeader("Access-Control-Max-Age",
 * "3600"); response.setHeader("Access-Control-Allow-Headers",
 * "Content-Type, Accept, X-Requested-With, remember-me");
 * 
 * chain.doFilter(req, res); }
 * 
 * @Override public void init(FilterConfig filterConfig) { }
 * 
 * @Override public void destroy() { }
 * 
 * }
 * 
 * 
 * 
 * @Override public void doFilter(ServletRequest req, ServletResponse res,
 * FilterChain chain) throws IOException, ServletException {
 * 
 * HttpServletRequest request = (HttpServletRequest) req; HttpServletResponse
 * response = (HttpServletResponse) res;
 * 
 * response.setHeader("Access-Control-Allow-Origin", );
 * response.setHeader("Access-Control-Allow-Credentials", "true");
 * response.setHeader("Access-Control-Allow-Methods",
 * "POST, GET, OPTIONS, DELETE"); response.setHeader("Access-Control-Max-Age",
 * "3600"); response.setHeader("Access-Control-Allow-Headers",
 * "Content-Type, Accept, X-Requested-With, remember-me");
 * 
 * chain.doFilter(req, res); }
 */