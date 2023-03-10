package com.alura.mudi.mudi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .authorizeHttpRequests()
                .requestMatchers("/home/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/usuario/pedido",true)
                        .permitAll()
                )
                .csrf().disable()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/home");
        return http.build();
    }
    @Bean
    UserDetailsManager users(DataSource dataSource) {
        /*UserDetails admin = User.builder()
                .username("admi")
                .password(passwordEncoder.encode("123456"))
                .roles("USER", "ADMIN")
                .build();*/
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        //users.createUser(admin);
        return users;
    }

}
