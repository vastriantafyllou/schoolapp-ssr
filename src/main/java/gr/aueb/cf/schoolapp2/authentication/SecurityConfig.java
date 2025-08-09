package gr.aueb.cf.schoolapp2.authentication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "index.html").permitAll()
                        .requestMatchers("/school/users/register").permitAll()
                        .requestMatchers("/school/teachers/insert").hasAuthority("EDIT_TEACHERS")
                        .requestMatchers(HttpMethod.GET,"/school/teachers/edit/{uuid}").hasAuthority("EDIT_TEACHERS")
                        .requestMatchers(HttpMethod.POST,"/school/teachers/edit").hasAuthority("EDIT_TEACHERS")
                        .requestMatchers(HttpMethod.GET,"/school/teachers/delete/{uuid}").hasAuthority("EDIT_TEACHERS")
                        .requestMatchers("/school/teachers/**").hasAnyRole("ADMIN", "TEACHERS_ADMIN")
                        .requestMatchers("/school/admin/**").hasRole("ADMIN")
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/img/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin
                        .loginPage("/login")
//                        .usernameParameter("username")
//                        .passwordParameter("password")
//                        .loginProcessingUrl("/login")
//                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/school/teachers", false)
                        .permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                );
                return http.build();


    }
}
