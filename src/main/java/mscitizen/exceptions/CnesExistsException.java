package mscitizen.exceptions;

public class CnesExistsException extends ResourceNotFoundException{


    public CnesExistsException(String cnes) {
        super("Não é possível concluir operação pois a unidade de saúde de cnes " + cnes + " já existe!");
    }
}
