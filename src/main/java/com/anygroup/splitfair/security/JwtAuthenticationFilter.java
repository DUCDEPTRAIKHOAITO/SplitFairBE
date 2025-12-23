package com.anygroup.splitfair.security;

import com.anygroup.splitfair.repository.UserRepository;
import com.anygroup.splitfair.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String path = request.getServletPath();


        if (path.startsWith("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);

        if (jwtUtil.validateToken(token)) {
            String email = jwtUtil.getEmailFromToken(token);

            userRepository.findByEmail(email).ifPresent(user -> {
                var authority =
                        new SimpleGrantedAuthority(user.getRole().getName().name());

                String password =
                        user.getPassword() != null ? user.getPassword() : "PROTECTED";

                var authUser = new org.springframework.security.core.userdetails.User(
                        user.getEmail(), password, List.of(authority));

                var authentication = new UsernamePasswordAuthenticationToken(
                        authUser, null, authUser.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        }

        filterChain.doFilter(request, response);
    }

}