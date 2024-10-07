package kz.tenko.spring.security.configuration;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@EnableWebSecurity
@Configuration
public class MySecurityConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    public UserDetailsService users() {

        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                .requestMatchers("/").hasAnyRole("EMPLOYEE", "HR", "MANAGER")
                .requestMatchers("/hr_info").hasAnyRole("HR")
                .requestMatchers("/manager_info").hasAnyRole("MANAGER")

                .anyRequest().authenticated()
                .and().formLogin().permitAll();
        return http.build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user1 = User.withDefaultPasswordEncoder()
//                .username("anna")
//                .password("anna")
//                .roles("EMPLOYEE")
//                .build();
//
//        UserDetails user2 = User.withDefaultPasswordEncoder()
//                .username("elena")
//                .password("elena")
//                .roles("HR")
//                .build();
//
//        UserDetails user3 = User.withDefaultPasswordEncoder()
//                .username("ivan")
//                .password("ivan")
//                .roles("HR", "MANAGER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2, user3);
//    }

}





















