package com.example.demo.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.filter.MemberAccountFilter;
import com.example.demo.filter.MemberFilter;

@Configuration
public class FilterConfig {

	@Bean
	public FilterRegistrationBean<MemberAccountFilter> setMemberAccountFilter() {
        FilterRegistrationBean<MemberAccountFilter> filterRegistrationBean = new FilterRegistrationBean<MemberAccountFilter>(new MemberAccountFilter());
        filterRegistrationBean.addUrlPatterns("/login", "/register");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;		
	}
	
	@Bean
	public FilterRegistrationBean<MemberFilter> setLoginFilter() {
        FilterRegistrationBean<MemberFilter> filterRegistrationBean = new FilterRegistrationBean<MemberFilter>(new MemberFilter());
        filterRegistrationBean.addUrlPatterns("/information", "/modifyPassword");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;		
	}
	
}
