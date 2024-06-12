
/*Changes Made:
Constants: Introduced AUTHORIZATION_HEADER and BEARER_PREFIX constants to avoid magic strings and improve readability.

Magic Strings refer to the use of hard-coded string values directly in the code. These strings are called "magic" because their meaning or purpose isn't immediately clear to someone reading the code, making the code harder to understand and maintain. 


Helper Methods: Created getJwtFromRequest and setAuthenticationForUser helper methods to separate concerns and make the doFilterInternal method cleaner.
Error Handling: Added a try-catch block around the main logic in doFilterInternal to log any exceptions that might occur.
Logging: Added logging for exception handling (assuming there is a logger configured in the class).
These changes improve the readability, maintainability, and reliability of the code.;*/

package com.growfin.ticketingSystem.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.growfin.ticketingSystem.services.CustomUserDetailsService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        try {
            String jwt = getJwtFromRequest(request);

            if (jwt != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                String username = tokenProvider.extractUsername(jwt);

                if (username != null) {
                    UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

                    if (tokenProvider.validateToken(jwt, userDetails)) {
                        setAuthenticationForUser(request, userDetails);
                    }
                }
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        chain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    private void setAuthenticationForUser(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
