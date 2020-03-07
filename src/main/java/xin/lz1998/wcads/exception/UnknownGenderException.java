package xin.lz1998.wcads.exception;

public class UnknownGenderException extends RuntimeException {
    public UnknownGenderException() {
        super("unknown gender");
    }

    public UnknownGenderException(String gender) {
        super(String.format("unknown gender: %s", gender));
    }

}
