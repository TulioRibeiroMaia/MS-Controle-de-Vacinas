package mscitizen.exceptions;

public class CpfAlreadyExistsException extends BadRequestException{
    public CpfAlreadyExistsException(String cpf) {
        super("Não é possível cadastrar um novo usuário pois o CPF " + cpf + " já está cadastrado!");
    }
}
