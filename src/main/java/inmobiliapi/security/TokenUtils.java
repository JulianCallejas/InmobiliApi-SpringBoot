
package inmobiliapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;


public class TokenUtils {
    
    private final static String ACCESS_TOKEN_SECRET = "SecretKeyDe256BytesSecretKeyDe256BytesSecretKeyDe256Bytes";
    private final static Long ACCESS_TOKEN_EXPIRATION_TIME = 1800000L;
    
    
    public static String createToken(String email){
        long expirationTime = ACCESS_TOKEN_EXPIRATION_TIME;
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        
        Map<String,Object> extra = new HashMap<>();  //Sirve para agregar mas campos al json respuesta del token
        
        return Jwts.builder()
                .setSubject(email)
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }
    
    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
        
        try {
            Claims claims = Jwts.parserBuilder()
                .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String email = claims.getSubject();
        return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (ExpiredJwtException  e) {
            System.out.println("expired " + e.toString());
            return new UsernamePasswordAuthenticationToken("expired", null, Collections.emptyList());
        }catch (JwtException e) {
            System.out.println(e.toString());
            return null;   
        }
    }
    
    
}
