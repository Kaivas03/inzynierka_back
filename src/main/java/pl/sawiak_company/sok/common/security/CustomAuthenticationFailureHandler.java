package pl.sawiak_company.sok.common.security;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e
    ) throws IOException {
        if (e instanceof DisabledException) {
            httpServletResponse.sendError(401, "notActivated");
        } else if (e instanceof LockedException) {
            httpServletResponse.sendError(401, "bannedAccount");
        } else {
            httpServletResponse.sendError(401, "credentialsError");
        }
    }
}