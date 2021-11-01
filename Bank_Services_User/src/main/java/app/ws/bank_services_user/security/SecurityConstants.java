package app.ws.bank_services_user.security;

public class SecurityConstants {

    public static final long EXPIRATION_TIME = 846000000;

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    public static final String TOKEN_SECRET = "0i5rco86lp8ja3v6konzlys1ks8xw81dowz0?=-01jznm0-9";

    public static final String SIGN_UP_CUSTOMER_URL = "/customers/login";

    public static final String SIGN_UP_COUNSELOR_URL = "/counselors";

    public static final String SIGN_IN_COUNSELOR_URL = "/customers";

    public static final String[] SWAGGER_UI= {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui.html/**",
            "/webjars/**",
    };

}

