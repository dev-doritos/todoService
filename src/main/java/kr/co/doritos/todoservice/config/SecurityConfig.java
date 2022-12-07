package kr.co.doritos.todoservice.config;

import kr.co.doritos.todoservice.common.UserRole;
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
            .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/office/**").hasRole(UserRole.ADMIN.getCode())
                .antMatchers("/todo/**").hasAnyRole(UserRole.ADMIN.getCode(), UserRole.GENERAL.getCode())
                .antMatchers("/member/**").hasAnyRole(UserRole.ADMIN.getCode(), UserRole.GENERAL.getCode())
                .anyRequest().authenticated()
            .and()
            .csrf()
                .disable()
            .formLogin()
                .loginPage("/login")
                .usernameParameter("uemail")
                .passwordParameter("upwd")
                .loginProcessingUrl("/login/proc")
                .successHandler((request, response, authentication) -> {
                    String ip = request.getRemoteAddr();
                    log.info("[{}] login 성공. authentication.getAuthorities()={}", ip, authentication.getAuthorities());

                    boolean isAdmin = authentication.getAuthorities().stream().anyMatch(e -> e.toString().equals(UserRole.ADMIN.getCode()));

                    String redirect = "";
                    if (isAdmin) {
                        redirect = "/office";
                    } else {
                        redirect = "/";
                    }
                    log.info("[{}] login 이동 페이지. redirect={}", ip, redirect);
                    response.sendRedirect(redirect);
                })
                .failureHandler((request, response, exception) -> {
                    String ip = request.getRemoteAddr();
                    log.info("[{}] login 실패. e.getMessage()={}", ip, exception.getMessage());
                    response.sendRedirect("/login?error");
                })
            .and()
            .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
