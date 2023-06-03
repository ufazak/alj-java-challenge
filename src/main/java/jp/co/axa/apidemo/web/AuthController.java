package jp.co.axa.apidemo.web;

import jp.co.axa.apidemo.domain.user.User;
import jp.co.axa.apidemo.domain.user.UserRepository;
import jp.co.axa.apidemo.infra.exception.InvalidCredentialsException;
import jp.co.axa.apidemo.infra.security.TokenService;
import jp.co.axa.apidemo.web.dto.AuthTokenRequest;
import jp.co.axa.apidemo.web.dto.AuthTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("token")
    public ResponseEntity<AuthTokenResponse> getAuthToken(@RequestBody AuthTokenRequest request) {
        User user = userRepository.findByUsernameAndPassword(
                request.getUsername(), request.getPassword()
        ).orElseThrow(() -> new InvalidCredentialsException("invalid credentials"));

        return ResponseEntity.ok(
                new AuthTokenResponse(
                        tokenService.generateAuthToken(user.getId(), request.getUsername())
                )
        );
    }
}
