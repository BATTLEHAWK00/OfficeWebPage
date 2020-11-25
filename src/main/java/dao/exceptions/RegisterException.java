package dao.exceptions;

/**
 * ◊¢≤·“Ï≥£¿‡
 */
public class RegisterException extends Exception {
    String msg;

    public RegisterException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}
