package mscitizen.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mscitizen.enums.State;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthCenterResponseDTO {

    private Long id;

    private String cnes;

    private String name;

    private String city;

    private State state;
}
