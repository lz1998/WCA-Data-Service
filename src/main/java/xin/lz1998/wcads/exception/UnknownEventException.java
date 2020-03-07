package xin.lz1998.wcads.exception;

public class UnknownEventException extends RuntimeException {
    public UnknownEventException() {
        super("unknown event");
    }

    public UnknownEventException(String event) {
        super(String.format("unknown event: %s", event));
    }
}
