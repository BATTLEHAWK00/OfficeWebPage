package dao.exceptions;

/**
 * ע���쳣��
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
