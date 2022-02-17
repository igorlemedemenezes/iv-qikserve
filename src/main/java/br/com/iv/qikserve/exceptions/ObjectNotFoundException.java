package br.com.iv.qikserve.exceptions;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException() {
        super("Object not found");
    }

    public ObjectNotFoundException(String msg) {
        super(msg);
    }
    
}