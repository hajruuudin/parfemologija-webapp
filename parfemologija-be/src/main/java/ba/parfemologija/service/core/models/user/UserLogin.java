package ba.parfemologija.service.core.models.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "Properties which are required for a user to login")
public class UserLogin implements Serializable {
    @Schema(description = "Username of the user")
    private String username;

    @Schema(description = "Password of the user (un-hashed when logging in, hashed when retrieving)")
    private String password;
}
