package edu.nitt.delta.bustracker.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.nitt.delta.bustracker.service.BusTrackerUserDetailsService;
import edu.nitt.delta.bustracker.utils.JwtTokenUtil;

@Component
public class BusTrackerFilter extends OncePerRequestFilter {

    @Autowired
    private BusTrackerUserDetailsService busTrackerUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        String mobileNumber = null;
        
        try {

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.split(" ")[1].trim();
                mobileNumber = jwtTokenUtil.getUsernameFromToken(token);
            }

            if (mobileNumber != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = busTrackerUserDetailsService.loadUserByUsername(mobileNumber);
    
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, 
                        null,
                        userDetails.getAuthorities()
                    );
                    
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
            
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            filterChain.doFilter(request, response);
        }

    }
}
