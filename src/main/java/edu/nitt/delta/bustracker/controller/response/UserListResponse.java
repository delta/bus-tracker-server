package edu.nitt.delta.bustracker.controller.response;

import java.util.List;

import edu.nitt.delta.bustracker.model.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserListResponse {
    private List<User> users;
    private String message;
}
