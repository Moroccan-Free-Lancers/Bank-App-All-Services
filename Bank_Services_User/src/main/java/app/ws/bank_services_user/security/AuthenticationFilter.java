package app.ws.bank_services_user.security;

import app.ws.bank_services_user.requests.CustomerLoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            CustomerLoginRequest creds = new ObjectMapper().readValue(request.getInputStream(), CustomerLoginRequest.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(creds.getMail(), creds.getPassword(), new ArrayList<>())
            );
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
       String userName = ((User) authResult.getPrincipal()).getUsername();

       String token = Jwts.builder()
               .setSubject(userName)
               .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
               .signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
               .compact();

        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
    }


}
