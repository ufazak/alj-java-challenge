package jp.co.axa.apidemo.infra.security.filter;

import jp.co.axa.apidemo.infra.security.auth.BearerTokenConfigurer.BearerAuthentication;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.security.core.context.SecurityContextHolder.clearContext;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@RequiredArgsConstructor
public class BearerTokenFilter extends OncePerRequestFilter {

    @SuppressWarnings("FieldCanBeLocal")
    private final String BEARER = "Bearer";
    private final Function<BearerAuthentication, Authentication> authentication;
    private final RequestMatcher protectedEndpointsMatcher;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = resolveToken(request);
        if (protectedEndpointsMatcher.matches(request) && Objects.nonNull(token)) {
            try {
                getContext().setAuthentication(authentication.apply(new BearerAuthentication(token)));
            } catch (Exception ex) {
                clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION);
        return Objects.nonNull(header) && header.startsWith(BEARER)
                ? header.substring(BEARER.length()).trim()
                : null;
    }
}
