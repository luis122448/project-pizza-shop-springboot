package luis122448.projectpizza.web.controller;

import lombok.extern.slf4j.Slf4j;
import luis122448.projectpizza.service.dto.LoginDto;
import luis122448.projectpizza.web.config.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto t){
        try {
            UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(t.getUsername(),t.getPassword());
            Authentication authentication = this.authenticationManager.authenticate(login);
            System.out.println(authentication.isAuthenticated());
            System.out.println(authentication.getPrincipal());
            String jwt = this.jwtUtil.create(t.getUsername());
            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }
}
