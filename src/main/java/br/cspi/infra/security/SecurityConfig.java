package br.cspi.infra.security;


import br.cspi.service.AutenticacaoService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final AuenticacaoFilter autenticacaoFilter;


@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(crsf -> crsf.disable())
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                            //endpoint de login - todos
                            .requestMatchers(HttpMethod.POST, "/login").permitAll()
                            .requestMatchers(HttpMethod.POST, "/owner").permitAll()
                            .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()

                            .requestMatchers(HttpMethod.GET, "/owner/**").hasAuthority("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/user/**").hasAnyAuthority("ADMIN","OWNER")

                            .requestMatchers(HttpMethod.DELETE, "/owner/**").hasAuthority("ADMIN")

                            .requestMatchers( HttpMethod.POST, "/user/**","/funcionario/**","/servico/**", "/produto/**", "/pet/**", "/cliente/**").hasAnyAuthority("OWNER", "ADMIN")
                            .requestMatchers( HttpMethod.PUT,"/user/**","/funcionario/**","/servico/**", "/produto/**", "/pet/**", "/cliente/**").hasAnyAuthority("OWNER", "ADMIN")
                            .requestMatchers( HttpMethod.DELETE,"/user/**","/funcionario/**","/servico/**", "/produto/**", "/pet/**", "/cliente/**").hasAnyAuthority("OWNER", "ADMIN")



                            .anyRequest().authenticated()
            )
            .addFilterBefore(this.autenticacaoFilter, UsernamePasswordAuthenticationFilter.class)
            .build(); // sessao nao salvs
}

   @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
   }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
