package jp.co.axa.apidemo.infra.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jp.co.axa.apidemo.infra.exception.InvalidTokenException;
import lombok.AllArgsConstructor;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static io.jsonwebtoken.SignatureAlgorithm.HS512;
import static java.time.Instant.now;
import static java.util.Date.from;

@AllArgsConstructor
public class JwtService {

    private final String jwtSecret;
    private final Duration tokenTtl;

    public String generate(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(HS512, jwtSecret)
                .setExpiration(newExpirationDate(tokenTtl))
                .compact();
    }

    public Claims parse(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception ex) {
            throw new InvalidTokenException(ex);
        }
    }

    private Date newExpirationDate(Duration ttl) {
        return from(now().plus(ttl));
    }
}
