package cn.leiax00.app.common.domain.exception;

public class NetworkSelectException extends Exception{
    public NetworkSelectException() {
    }

    public NetworkSelectException(String message) {
        super(message);
    }

    public NetworkSelectException(String message, Throwable cause) {
        super(message, cause);
    }

    public NetworkSelectException(Throwable cause) {
        super(cause);
    }
}
