package xin.lz1998.wcads.exception;

public class UnknownRegionException extends RuntimeException {
    public UnknownRegionException(String region) {
        super(String.format("unknown region: %s", region));
    }
}

