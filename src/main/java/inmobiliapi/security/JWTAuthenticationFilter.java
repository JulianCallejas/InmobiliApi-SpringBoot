package inmobiliapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Collections;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        AuthCredentials authCredentials = new AuthCredentials();
        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(), AuthCredentials.class);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }

        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(authCredentials.getEmail(), authCredentials.getContrasena(), Collections.emptyList());
        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        try {
            UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authResult.getPrincipal();
            String token = TokenUtils.createToken(userDetailsImpl.getUsername());

            TokenBodyResponse tokenBodyResponse = new TokenBodyResponse(token);
            ObjectMapper mapper = new ObjectMapper();

            response.addHeader("Authorization", "Bearer " + token);
            response.setContentType("application/json");
            response.getWriter().append(mapper.writeValueAsString(tokenBodyResponse));
            response.getWriter().flush();

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        super.successfulAuthentication(request, response, chain, authResult); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        if (!response.isCommitted()) {
            UnsuccessfulAuthenticationResponse uar = new UnsuccessfulAuthenticationResponse();
            ObjectMapper mapper = new ObjectMapper();
            //response.setStatus(400);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType("application/json");
            response.getWriter().append(mapper.writeValueAsString(uar));
            response.getWriter().close();

        }

        super.unsuccessfulAuthentication(request, response, failed); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

    }

}
