package ba.parfemologija.service.core.models.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Schema(description = "Properties for accessing the current logged in users information")
public class LoggedUserModel implements Serializable {
    @Schema(description = "Unique ID of the user")
    private Long id;

    @Schema(description = "User username")
    private String username;

    @Schema(description = "First name of the user")
    private String name;

    @Schema(description = "Last name of the user")
    private String surname;

    @Schema(description = "Email of the user")
    private String email;

    @Schema(description = "The users phone number")
    private String phoneNumber;

    @Schema(description = "Location of the user")
    private String location;

    @Schema(description = "Status of the user (admin or regular)")
    private Boolean isAdmin;

    @Schema(description = "Date and time when the user joined")
    private LocalDateTime joinedAt;
}
