package inmobiliapi.security;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    @Autowired
    private final UserDetailsService userDetailsService;
    @Autowired
    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
        return bCryptPasswordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager) throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authManager);
        jwtAuthenticationFilter.setFilterProcessesUrl("/v1/auth/login");

        RequestMatcher[] availableEndPoints = new RequestMatcher[]{
            new AntPathRequestMatcher("/v1/auth/login"),
            new AntPathRequestMatcher("/v1/inmuebles/availables"),
            new AntPathRequestMatcher("/v1/inmuebles/*"),
            new AntPathRequestMatcher("/v1/inmuebles"),
            new AntPathRequestMatcher("/v1/usuarios"),
            new AntPathRequestMatcher("/v3/api-docs"),
            new AntPathRequestMatcher("/v3/api-docs/*"),
            new AntPathRequestMatcher("/v3/api-docs/swagger-config"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-ui/*")

        };

        //.anyRequest().authenticated()
        http
                .csrf().disable()
                .cors()
                .configurationSource(request -> {
                    var cors = new CorsConfiguration();
                    cors.setAllowedOrigins(List.of("http://localhost:4200", "http://127.0.0.1:80", "*"));
                    cors.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
                    cors.setAllowedHeaders(List.of("*"));
                    return cors;
                })
                .and()
                .authorizeHttpRequests()
                .requestMatchers(availableEndPoints).permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(jwtAuthenticationFilter)
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Bean
//    UserDetailsService userDetailsService() throws Exception {
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        try {
//            manager.createUser(User.withUsername("admin")
//                    .password(passwordEncoder().encode("admin"))
//                    .roles()
//                    .build());
//        } catch (Exception e) {
//            return null;
//        }
//        return manager;
//
//    }
    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {

        try {
            return http.getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder())
                    .and()
                    .build();
        } catch (Exception e) {
            System.out.println("prueba1 " + e.toString());
            return null;
        }

    }

//    public static void main(String[] args) {
//        System.out.println(new BCryptPasswordEncoder().encode("contraseñaIncorrecta202235486132465MaFGSrGATSUha"));
//        
//        HashMap<String, Object> resp = new HashMap<>();
//        resp.put("statusCode", 400);
//        resp.put("message", "Datos Incorrectos");
//        resp.put("error", "Bad Request");
//
//        Gson pru = new Gson();
//        
//        System.out.println(pru.toJson(resp));
//
//        System.out.println(resp);
//        JsonObject json = new JsonObject();
//        json.addProperty("statusCode", 400);
//        json.addProperty("message", "Datos Incorrectos");
//        json.addProperty("error", "Bad Request");
//
//        System.out.println(json);
//    }
}
