package kr.pe.aqua;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.pe.aqua.interceptor.JwtInterceptor;

@SpringBootApplication
public class Step03TutorialJwtApplication implements WebMvcConfigurer{

	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		System.out.println("addInterceptors() --");
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/api/**").addPathPatterns("/api/fish/**")
		.excludePathPatterns("/api/logincheck/**")
		.excludePathPatterns(Arrays.asList("/css/**","/script/**","/plugin/**"))
		.excludePathPatterns("/api/members");
		
	}
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println("addCorsMappings() --");
		registry.addMapping("/**")
		.allowedOrigins("*")
		.allowedMethods("*")
		.allowedHeaders("*")
		.allowedHeaders("jwt-auth-token");
	}
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(Step03TutorialJwtApplication.class, args);
	}

}
