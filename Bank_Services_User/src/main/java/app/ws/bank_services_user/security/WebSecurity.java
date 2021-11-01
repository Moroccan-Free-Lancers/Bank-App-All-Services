package app.ws.bank_services_user.security;

import app.ws.bank_services_user.services.CustomerService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final CustomerService customerDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(CustomerService customerDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerDetailsService = customerDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();
        http.csrf().disable();
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_CUSTOMER_URL)
                .permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_COUNSELOR_URL)
                .permitAll()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_IN_COUNSELOR_URL)
                .permitAll()
                .antMatchers(HttpMethod.GET, SecurityConstants.SWAGGER_UI)
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(getAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()));

    }

    protected AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/customers/login");
        return filter;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customerDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
