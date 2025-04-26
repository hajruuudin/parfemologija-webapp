package ba.parfemologija.service.core.models.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "Properties of a user entity registration entity")
public class UserCreate implements Serializable {
    @Schema(description = "User username")
    private String username;

    @Schema(description = "First name of the user")
    private String name;

    @Schema(description = "Last name of the user")
    private String surname;

    @Schema(description = "Email of the user")
    private String email;

    @Schema(description = "Hashed password of the user")
    private String password;

    @Schema(description = "The users phone number")
    private String phoneNumber;

    @Schema(description = "Location of the user")
    private String location;
}
