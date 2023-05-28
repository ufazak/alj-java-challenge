package jp.co.axa.apidemo.infra.security;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtService jwtService;

    public TokenData checkAndParse(String token) {
        Claims claims = jwtService.parse(token);
        return new TokenData(Long.valueOf(claims.getSubject()));
    }
}
