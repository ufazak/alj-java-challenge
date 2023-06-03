package jp.co.axa.apidemo.infra.security;

import io.jsonwebtoken.Claims;
import jp.co.axa.apidemo.infra.exception.InvalidTokenException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtService jwtService;

    public String generateAuthToken(Long userId, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("username", username);
        return jwtService.generate(claims);
    }

    public TokenData checkAndParse(String token) {
        try {
            Claims claims = jwtService.parse(token);
            return new TokenData(Long.valueOf(claims.get("userId").toString()));
        } catch (Exception ex) {
            throw new InvalidTokenException("invalid token");
        }
    }

    @Data
    public static class TokenData {
        private final Long userId;
    }
}
