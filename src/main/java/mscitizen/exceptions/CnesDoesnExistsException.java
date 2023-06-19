package mscitizen.exceptions;

public class CnesDoesnExistsException extends ResourceNotFoundException{

    public CnesDoesnExistsException(String cnes) {
        super("Não é possível concluir operação pois o " + cnes + " não foi encontrado");
    }
}
