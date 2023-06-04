package jp.co.axa.apidemo.infra;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RequestMatchers {
    public static final List<String> PUBLIC_AUTH_REQUEST_MATCHERS = Collections.singletonList(
            "/api/v1/auth/token"
    );

    public static final List<String> BEARER_TOKEN_REQUEST_MATCHERS = Arrays.asList(
            "/api/v1/employees/**",
            "/api/v1/history/**"
    );
}
