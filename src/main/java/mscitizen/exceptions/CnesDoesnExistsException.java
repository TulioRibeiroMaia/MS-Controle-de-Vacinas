package mscitizen.exceptions;

public class CnesDoesnExistsException extends ResourceNotFoundException{

    public CnesDoesnExistsException(String cnes) {
        super("Não é possível concluir operação pois a unidade de saúde de cnese" + cnes + " não foi encontrado");
    }
}
