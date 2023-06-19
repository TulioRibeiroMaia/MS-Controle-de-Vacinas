package mscitizen.exceptions;

public class VaccineIDDoesntExistsException extends ResourceNotFoundException {


    public VaccineIDDoesntExistsException(Long id) {
        super("Não é possível concluir operação pois a vacina id " + id + " não foi encontrado");
    }
}
