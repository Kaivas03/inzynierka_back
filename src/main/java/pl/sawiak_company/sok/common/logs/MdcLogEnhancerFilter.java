package pl.sawiak_company.sok.common.logs;

import jakarta.servlet.*;
import org.slf4j.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MdcLogEnhancerFilter implements Filter {
    /**
     * Overriding default method.
     * This implementation is a NOP.
     */
    @Override
    public void destroy() {
    }

    /**
     * Overriding default method.
     * This implementation is a NOP.
     */
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        MDC.put("userId", username);
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
