package inmobiliapi.security;

import com.google.gson.JsonObject;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            String token = bearerToken.replace("Bearer ", "");
            UsernamePasswordAuthenticationToken usernamePAT = TokenUtils.getAuthentication(token);
            if (usernamePAT.getPrincipal().toString().equals("expired")) {
                JsonObject jsonRes = new JsonObject();
                jsonRes.addProperty("statusCode", 401);
                jsonRes.addProperty("message", "Expired Token");
                response.setStatus(401);
                response.getWriter().append(jsonRes.toString());
                response.getWriter().close();
                return;
            }
            SecurityContextHolder.getContext().setAuthentication(usernamePAT);
        } else {
//            JsonObject jsonRes = new JsonObject();
//            jsonRes.addProperty("statusCode", 401);
//            jsonRes.addProperty("message", "Unauthorized");
//            response.setStatus(401);
//            response.getWriter().append(jsonRes.toString());
//            response.getWriter().close();
//            return;
        }
        filterChain.doFilter(request, response);
    }

}
