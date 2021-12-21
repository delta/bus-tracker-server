package edu.nitt.delta.bustracker.controller.api;

import edu.nitt.delta.bustracker.controller.request.AuthenticationRequest;
import edu.nitt.delta.bustracker.controller.response.AuthenticationResponse;
import edu.nitt.delta.bustracker.controller.response.DAuthTokenResponse;
import edu.nitt.delta.bustracker.controller.response.DAuthUserDetailsResponse;
import edu.nitt.delta.bustracker.utils.JwtTokenUtil;
import edu.nitt.delta.bustracker.utils.WebClientUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final WebClient dAuthWebClient;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            JwtTokenUtil jwtTokenUtil,
            WebClientUtil webClientUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.dAuthWebClient = webClientUtil.dAuthWebClient();
    }

    @Value("${busTracker.host}")
    private String clientHost;

    @Value("${busTracker.client.id}")
    private String clientId;

    @Value("${busTracker.client.secret}")
    private String clientSecret;

    @PostMapping("/driver/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest requestBody) {
        String mobileNumber = requestBody.getMobileNumber();
        String password = requestBody.getPassword();

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(mobileNumber, password));
        } catch (Exception e) {
            return new ResponseEntity<>(
                    AuthenticationResponse.builder().message(e.getMessage()).build(),
                    HttpStatus.FORBIDDEN);
        }

        String jwt = jwtTokenUtil.generateToken(mobileNumber);

        return new ResponseEntity<>(
                AuthenticationResponse.builder().jwt(jwt).message("OK").build(),
                HttpStatus.ACCEPTED);
    }

    @GetMapping("/callback")
    public RedirectView dAuthCallBack(@RequestParam String code) {
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.put("client_id", Collections.singletonList(clientId));
            params.put("client_secret", Collections.singletonList(clientSecret));
            params.put("grant_type", Collections.singletonList("authorization_code"));
            params.put("code", Collections.singletonList(code));
            params.put("redirect_uri", Collections.singletonList(clientHost + "/auth/callback"));

            DAuthTokenResponse tokenResponse =
                    dAuthWebClient
                            .post()
                            .uri("/api/oauth/token")
                            .body(BodyInserters.fromFormData(params))
                            .retrieve()
                            .bodyToMono(DAuthTokenResponse.class)
                            .block();
            if (tokenResponse == null) throw new Exception("Error fetching token");

            DAuthUserDetailsResponse userDetailsResponse =
                    dAuthWebClient
                            .post()
                            .uri("/api/resources/user")
                            .header(
                                    HttpHeaders.AUTHORIZATION,
                                    "Bearer " + tokenResponse.getAccess_token())
                            .retrieve()
                            .bodyToMono(DAuthUserDetailsResponse.class)
                            .block();
            if (userDetailsResponse == null) throw new Exception("Error fetching user details");

            String jwt =
                    jwtTokenUtil.generateStudentToken(
                            userDetailsResponse.getEmail().substring(0, 9),
                            userDetailsResponse.getName());

            return new RedirectView("bus://" + clientHost.split("://")[1] + "?jwt=" + jwt);
        } catch (Exception e) {
            return new RedirectView(
                    "bus://" + clientHost.split("://")[1] + "?jwt=&error=" + e.getMessage());
        }
    }
}
