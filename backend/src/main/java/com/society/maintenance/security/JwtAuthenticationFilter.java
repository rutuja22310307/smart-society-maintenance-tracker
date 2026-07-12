package com.society.maintenance.security;


import jakarta.servlet.*;
import jakarta.servlet.http.*;


import org.springframework.security.authentication.*;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;

import java.io.IOException;



@Component
public class JwtAuthenticationFilter 
        extends OncePerRequestFilter {



    private final JwtService jwtService;


    public JwtAuthenticationFilter(
            JwtService jwtService
    ){

        this.jwtService = jwtService;

    }



    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    )
    throws ServletException, IOException {


        String authHeader =
                request.getHeader("Authorization");


        if(authHeader != null &&
                authHeader.startsWith("Bearer ")) {


            String token =
                    authHeader.substring(7);


            String email =
                    jwtService.extractEmail(token);


            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            null
                    );


            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);

        }


        filterChain.doFilter(
                request,
                response
        );

    }

}