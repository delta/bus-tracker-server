package edu.nitt.delta.bustracker.config;

import edu.nitt.delta.bustracker.model.Role;
import edu.nitt.delta.bustracker.service.BusTrackerUserDetailsService;
import edu.nitt.delta.bustracker.utils.JwtTokenUtil;
import edu.nitt.delta.bustracker.utils.WebClientUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BusTrackerUserDetailsService busTrackerUserDetailsService;
    private final DriverAuthFilter driverAuthFilter;

    @Autowired
    public WebSecurityConfig(
            BusTrackerUserDetailsService busTrackerUserDetailsService,
            DriverAuthFilter driverAuthFilter
    ) {
        this.busTrackerUserDetailsService = busTrackerUserDetailsService;
        this.driverAuthFilter = driverAuthFilter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/auth/**")
                .permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/location",
                        "/vehicle/**",
                        "driver/**")
                .permitAll()
                .antMatchers(HttpMethod.POST, "/driver", "/vehicle")
                .hasRole(Role.ADMIN.toString())
                .anyRequest().hasAnyRole(Role.ADMIN.toString(), Role.DRIVER.toString());

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(
                driverAuthFilter,
                UsernamePasswordAuthenticationFilter.class
        );
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(busTrackerUserDetailsService).passwordEncoder(this.passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtilBean() {
        return new JwtTokenUtil();
    }

    @Bean
    public WebClientUtil webClientUtilBean() {
        return new WebClientUtil();
    }
}
