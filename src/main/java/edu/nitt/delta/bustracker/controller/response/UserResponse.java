package edu.nitt.delta.bustracker.controller.response;

import edu.nitt.delta.bustracker.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private User user;
    private String message;
}
