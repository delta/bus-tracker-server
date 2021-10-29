package edu.nitt.delta.bustracker.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nitt.delta.bustracker.controller.request.AuthenticationRequest;
import edu.nitt.delta.bustracker.controller.response.AuthenticationResponse;
import edu.nitt.delta.bustracker.service.BusTrackerUserDetailsService;
import edu.nitt.delta.bustracker.utils.JwtTokenUtil;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BusTrackerUserDetailsService busTrackerUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    
    @PostMapping
    public ResponseEntity<AuthenticationResponse> Login(@RequestBody AuthenticationRequest requestBody) {
        String mobileNumber = requestBody.getMobileNumber();
        String password = requestBody.getPassword();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mobileNumber, password));
        } catch (Exception e) {
            return new ResponseEntity<>(AuthenticationResponse
                .builder()
                .message(e.getMessage())
                .build(), 
                HttpStatus.FORBIDDEN
            );
        }
        
        UserDetails userDetails = busTrackerUserDetailsService.loadUserByUsername(mobileNumber);
        String jwt = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity<>(AuthenticationResponse
            .builder()
            .jwt(jwt)
            .message("OK")
            .build(), 
            HttpStatus.ACCEPTED
        );
    }
}
