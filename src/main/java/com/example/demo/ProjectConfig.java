package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class ProjectConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
    {

//        http.httpBasic(c -> {
//            c.realmName("OTHER");
//            c.authenticationEntryPoint(new CustomEntryPoint());
//                });
        http.authorizeHttpRequests((authz) -> authz.anyRequest().authenticated())
                .formLogin()
                //.successHandler(authenticationSuccessHandler)
                //.failureHandler(authenticationFailureHandler)
                .and()
                .httpBasic();

        return http.build();
    }

/*
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)
            throws Exception {

        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        User user = (User) User.withUsername("jimmy")
                .password("12345")
                .authorities("read")
                .build();

        userDetailsService.createUser(user);

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .and()
                .build();
    }*/

//    @Autowired
//    private CustomAuthenticationProvider authProvider;

//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(authProvider);
//        return authenticationManagerBuilder.build();
//    }

    //@Bean
    //public UserDetailsService userDetailsService(DataSource dataSource) {
    //    return new JdbcUserDetailsManager(dataSource);
        //InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        /*User user = (User) User.withUsername("jimmy")
                .password("12345")
                .authorities("read")
                .build();

        userDetailsService.createUser(user);

        return userDetailsService;*/

        /*UserDetails u= new User("jimmy", "12345", "read");
        List<UserDetails> users = List.of(u);
        return new InMemoryUserDetailsService(users);*/
    //}

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
}
