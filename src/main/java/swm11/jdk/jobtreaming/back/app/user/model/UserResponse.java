package swm11.jdk.jobtreaming.back.app.user.model;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {

    private String token;
    private User user;

}
