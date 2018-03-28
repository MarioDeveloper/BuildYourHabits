package com.everydayhabits.product.module.web.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;

public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

//    @Override
//    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
//        super.beforeSpringSecurityFilterChain(servletContext);
//
//        FilterRegistration.Dynamic characterEncodingFilter;
//        characterEncodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
//        characterEncodingFilter.setInitParameter("encoding", "UTF-8");
//        characterEncodingFilter.setInitParameter("forceEncoding", "true");
//        characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
//    }


    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        super.beforeSpringSecurityFilterChain(servletContext);

        FilterRegistration.Dynamic characterEncodingFilter;
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);
        characterEncodingFilter = servletContext.addFilter("encodingFilter", encodingFilter);
        characterEncodingFilter.addMappingForUrlPatterns(null, false, "/*");
        System.out.println("Jestem w WebSecurityConfigurerAdapter");

    }

}