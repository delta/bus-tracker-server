package edu.nitt.delta.bustracker.controller.response;

import lombok.Data;

@Data
public class DAuthUserDetailsResponse {
    private String id;
    private String email;
    private String name;
    private String phoneNumber;
    private String gender;
    private String createdAt;
    private String updatedAt;
}
