package com.walter.springboot.config.auth;

import com.walter.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //spring security 설정들을 활성화 시킴
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .headers().frameOptions().disable()//h2-console을 활성화시키기 위해 옵션들을 disable시킴
                .and().authorizeRequests()//URL별 권한 관리를 설정하는 옵션의 시작
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**")
                //antMatchers : 권한 관리 대상을 지정하는 옵션. "/"등 지정된 URL들은 permitAll()옵션을 통해 전체 열람 권한을 줌
                .permitAll().antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //anyRequest : 설정된 값들 이외 나머지 URL들을 나타냄. authenticated를 이용하여 나머지 URL들은 모두 인증된(로그인한) 사용자들에게만 허용하도록 함
                .anyRequest().authenticated()//
                .and().logout().logoutSuccessUrl("/")
                .and().oauth2Login().userInfoEndpoint().userService(customOAuth2UserService);
    }
}
