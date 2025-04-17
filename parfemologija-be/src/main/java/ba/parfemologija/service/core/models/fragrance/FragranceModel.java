package ba.parfemologija.service.core.models.fragrance;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Schema(description = "Fragrance model for all properties of a fragrance entity")
public class FragranceModel implements Serializable {
}
