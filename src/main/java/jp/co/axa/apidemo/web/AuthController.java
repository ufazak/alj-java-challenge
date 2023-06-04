package jp.co.axa.apidemo.web;

import jp.co.axa.apidemo.config.properties.SystemAuthProperties;
import jp.co.axa.apidemo.domain.user.User;
import jp.co.axa.apidemo.domain.user.UserRepository;
import jp.co.axa.apidemo.infra.security.TokenService;
import jp.co.axa.apidemo.web.dto.AuthTokenRequest;
import jp.co.axa.apidemo.web.dto.AuthTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@SuppressWarnings("InnerClassMayBeStatic")
public class AuthController {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    private final SystemAuthProperties systemAuthProperties;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("token")
    public ResponseEntity<AuthTokenResponse> getAuthToken(@RequestBody AuthTokenRequest request) {
        User user = userRepository.findByUsername(request.getUsername());

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.ok(
                    new AuthTokenResponse(
                            tokenService.generateAuthToken(user.getId(), request.getUsername())
                    )
            );
        } else {
            throw new BadCredentialsException("invalid credentials");
        }
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onStartup() {
        userRepository.save(new User(
                systemAuthProperties.getLogin(),
                passwordEncoder.encode(systemAuthProperties.getPassword())
        ));
    }
}
