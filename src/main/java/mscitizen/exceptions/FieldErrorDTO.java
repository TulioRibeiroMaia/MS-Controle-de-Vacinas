package mscitizen.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FieldErrorDTO {

    private String field;
    private String error;
}
