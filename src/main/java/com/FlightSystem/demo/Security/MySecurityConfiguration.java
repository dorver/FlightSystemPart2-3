package com.FlightSystem.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT \"UserName\", \"Password\", \"Enabled\" from \"Users\" where \"UserName\"=?")
                .authoritiesByUsernameQuery("SELECT \"Users\".\"UserName\",\"User_Roles\".\"Role_Name\" FROM \"Users\" JOIN \"User_Roles\" ON \"Users\".\"User_Role\" = \"User_Roles\".\"Id\" WHERE \"UserName\" = ?")
        ;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/customer/**").hasAnyRole("CUSTOMER","ADMIN")
                .antMatchers("/airline/**").hasAnyRole("AIRLINE","ADMIN")
                .antMatchers("/").permitAll() //allow any user to connect to the page
                .and().formLogin();
    }
}
