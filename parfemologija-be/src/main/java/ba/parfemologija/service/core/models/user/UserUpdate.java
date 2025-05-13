package ba.parfemologija.service.core.models.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
@Schema(description = "Properties of a user entity update request")
public class UserUpdate implements Serializable {

    @NotNull
    @Schema(description = "Unique identifier of the user")
    private Long id;

    @Schema(description = "Username of the user")
    private String username;

    @Schema(description = "First name of the user")
    private String name;

    @Schema(description = "Last name of the user")
    private String surname;

    @Schema(description = "Email address of the user")
    private String email;

    @Schema(description = "Plain-text password for update")
    private String password;

    @Schema(description = "Phone number of the user")
    private String phoneNumber;

    @Schema(description = "Location of the user")
    private String location;

    @Schema(description = "Admin flag (true = admin)")
    private Boolean isAdmin;
}
