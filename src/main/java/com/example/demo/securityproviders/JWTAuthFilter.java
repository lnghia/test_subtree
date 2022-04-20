package com.example.demo.securityproviders;

import com.example.demo.configurations.security.PermittedUrlsUtil;
import com.example.demo.dto.responses.ResponseBodyDTO;
import com.example.demo.exceptions.InvalidTokenException;
import com.example.demo.userdetails.CustomUserDetails;
import com.example.demo.entities.UserEntity;
import com.example.demo.services.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private PermittedUrlsUtil permittedUrlsUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        Long userId = null;
        CustomUserDetails customUserDetails = null;
        String jwt = extractJWTFromHeader(httpServletRequest);

        if (!permittedUrlsUtil.isPermitted(httpServletRequest.getRequestURI())) {
            try {
                if (jwtProvider.validateToken(jwt)) {

                    userId = Long.valueOf(jwtProvider.getUserIdFromJWT(jwt));

                    if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                        UserEntity user = userService.getUserById(userId);

                        if (user != null) {
                            customUserDetails = new CustomUserDetails(user);

                            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
                            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

                            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        }
                    }
                }
            } catch (InvalidTokenException exception) {
                ResponseBodyDTO responseBodyDTO = new ResponseBodyDTO();

                responseBodyDTO.getErrors().put("JWT token", "Invalid token");

                httpServletResponse.setStatus(UNAUTHORIZED.value());
                httpServletResponse.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(httpServletResponse.getOutputStream(), responseBodyDTO);
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String extractJWTFromHeader(HttpServletRequest request) {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
        }

        return jwt;
    }
}
