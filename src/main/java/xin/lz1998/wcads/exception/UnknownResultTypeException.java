package xin.lz1998.wcads.exception;

public class UnknownResultTypeException extends RuntimeException {
    public UnknownResultTypeException() {
        super("unknown resultType");
    }

    public UnknownResultTypeException(String resultType) {
        super(String.format("unknown resultType: %s", resultType));
    }
}

