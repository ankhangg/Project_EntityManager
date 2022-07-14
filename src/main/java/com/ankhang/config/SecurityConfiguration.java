package com.ankhang.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ankhang.service.impl.UserDetailsServiceImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import com.ankhang.service.impl.UserDetailsServiceImpl;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private SecurityConfigBean securityConfigBean;
	


	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// Sét đặt dịch vụ để tìm kiếm User trong Database.
		// Và sét đặt PasswordEncoder.
		auth.userDetailsService(userDetailsService).passwordEncoder(securityConfigBean.passwordEncoder());
	}
   
	
    @Override
	protected void configure(HttpSecurity http) throws Exception {
    	
    	PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

		http.csrf().disable();
		
        // Các trang không cần đăng nhập
      http.authorizeRequests().antMatchers("/home","/").permitAll();

		// Các yêu cầu phải login với vai trò ROLE_EMPLOYEE hoặc ROLE_MANAGER.
		// Nếu chưa login, nó sẽ redirect tới trang /admin/login.
		http.authorizeRequests().antMatchers("/admin")//
				.access("hasRole('ROLE_ADMIN')");

		// Các trang chỉ dành cho MANAGER
		//http.authorizeRequests().antMatchers("/admin/product").access("hasRole('ROLE_MANAGER')");

	
			

		// Khi người dùng đã login, với vai trò XX.
		// Nhưng truy cập vào trang yêu cầu vai trò YY,
		// Ngoại lệ AccessDeniedException sẽ ném ra.
		http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

		// Cấu hình cho Login Form.
		http.authorizeRequests().and().formLogin()//

				// 
				.loginProcessingUrl("/j_spring_security_check") // Submit URL
				.loginPage("/login")//
				.defaultSuccessUrl("/home")//
				.failureUrl("/login?error=true&alert=danger")//
				.usernameParameter("userName")//
				.passwordParameter("passWord")
				//.passwordParameter(encoder.encode("passWord"))
				//.passwordParameter("password")//
		        //.passwordParameter("{bcrypt}$2a$10$0xuAeiVTDkmQHawSUiTSG.36.PQ9amIV4BBWHZus5ov3VdELc8ADW")
				.and().logout().logoutUrl("/logout").logoutSuccessUrl("/home");

	}
}
