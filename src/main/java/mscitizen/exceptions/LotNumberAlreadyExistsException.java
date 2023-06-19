package mscitizen.exceptions;

public class LotNumberAlreadyExistsException extends BadRequestException{

    public LotNumberAlreadyExistsException (String lotNumber) {
        super("Este lote " + lotNumber + " de vacinas já está cadastrado!");
    }
}
