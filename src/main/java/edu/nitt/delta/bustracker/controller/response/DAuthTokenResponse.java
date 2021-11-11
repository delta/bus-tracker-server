package edu.nitt.delta.bustracker.controller.response;

import lombok.Data;

@Data
public class DAuthTokenResponse {
    private String token_type;
    private String access_token;
    private String state;
    private String expires_in;
    private String id_token;
}
