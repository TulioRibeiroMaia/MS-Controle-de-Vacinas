package mscitizen.exceptions;

public class VaccineNameAndManufacturerAlreadyExistsException extends BadRequestException{

    public VaccineNameAndManufacturerAlreadyExistsException(String vaccineName, String manufacturer) {
        super("Vacina " + vaccineName + " já cadastrada e fabricante " + manufacturer + " já cadastrados");
    }
}
