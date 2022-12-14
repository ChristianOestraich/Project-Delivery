package com.app.projectdelivery.security;

import com.app.projectdelivery.model.UserModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticateFilter extends UsernamePasswordAuthenticationFilter
{
    public static final int TOKEN_EXPIRATION = 600_000;
    public static final String TOKEN_PASSWORD = "da1ac229-9b0d-4779-b528-5d23be3befe1";
    private final AuthenticationManager authenticationManager;

    public JWTAuthenticateFilter( AuthenticationManager authenticationManager )
    {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication( HttpServletRequest request,
                                                 HttpServletResponse response ) throws AuthenticationException
    {
        try
        {
            UserModel user = new ObjectMapper().readValue( request.getInputStream(), UserModel.class );

            return authenticationManager.authenticate( new UsernamePasswordAuthenticationToken
            (
               user.getUsername(),
               user.getPassword(),
               new ArrayList<>()
            ) );
        }

        catch ( IOException e )
        {
            throw new RuntimeException( "User authentication failed", e );
        }
    }

    @Override
    protected void successfulAuthentication( HttpServletRequest request, HttpServletResponse response,
                                             FilterChain chain, Authentication authResult ) throws IOException, ServletException
    {
        AppUserDetails userDate = (AppUserDetails) authResult.getPrincipal();

        String token = JWT.create()
                          .withSubject( userDate.getUsername() )
                          .withExpiresAt( new Date( System.currentTimeMillis() + TOKEN_EXPIRATION ) )
                          .sign( Algorithm.HMAC512( TOKEN_PASSWORD ) );

        response.getWriter().write( token );
        response.getWriter().flush();
    }
}
