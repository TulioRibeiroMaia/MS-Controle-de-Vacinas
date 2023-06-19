package mscitizen.exceptions;

public class CpfDoesntExistsException extends ResourceNotFoundException{


    public CpfDoesntExistsException(String cpf) {
        super("Não é possível conclui operação pois o CPF " + cpf + " não foi encontrado");
    }
}
