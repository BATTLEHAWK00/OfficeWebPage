package dao.exceptions;

public class LoginException extends Exception {
    String msg;

    public LoginException(String msg) {
        this.msg = msg;
    }

    @Override
    public String getMessage() {
        return msg;
    }
}