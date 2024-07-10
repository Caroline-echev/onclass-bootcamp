    package com.bootcamp.onclass.configuration.jwt;


    import jakarta.servlet.FilterChain;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpHeaders;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.context.SecurityContextHolder;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
    import org.springframework.stereotype.Component;
    import org.springframework.util.StringUtils;
    import org.springframework.web.filter.OncePerRequestFilter;

    import java.io.IOException;
    import java.util.Collection;
    import java.util.List;

    @Component
    @RequiredArgsConstructor
    public class JwtAuthenticationFilter extends OncePerRequestFilter {
        private final JwtService jwtService;
        private final UserDetailsService userDetailsService;
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {

            String token = getTokenFromRequest(request);
            if (token == null) {
                filterChain.doFilter(request, response);
                return;
            }

            String role = jwtService.extractRoleFromToken(token);

            Authentication authentication = new UsernamePasswordAuthenticationToken(null, null, getAuthorities(role));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        }
        private String getTokenFromRequest(HttpServletRequest request) {
            final String authHeader=request.getHeader(HttpHeaders.AUTHORIZATION);

            if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer "))
            {
                return authHeader.substring(7);
            }
            return null;
        }
        private Collection<? extends GrantedAuthority> getAuthorities(String role) {
            return List.of(new SimpleGrantedAuthority( role));
        }

    }
