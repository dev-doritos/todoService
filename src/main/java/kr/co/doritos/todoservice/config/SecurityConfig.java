package kr.co.doritos.todoservice.config;

import kr.co.doritos.todoservice.common.UserRole;
import kr.co.doritos.todoservice.handler.LoginFailureHandler;
import kr.co.doritos.todoservice.handler.LoginSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/h2-console/**", "/webjars/**", "favicon.ico", "/css/**", "/js/**");
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/office/**").hasRole(UserRole.ROLE_ADMIN.getCode())
                .anyRequest().authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("uemail")
                .passwordParameter("upwd")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler())
            .and()
            .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
            .and()
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
