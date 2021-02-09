package bean;

import com.google.gson.Gson;
import lombok.Data;

/**
 * ��Ӧ����
 */
public class Response {
    private Object data;
    private String message;

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public Response(String message) {
        this.message = message;
        data = new Object();
    }

    public Response() {
    }

    public void SetMessage(String message) {
        this.message = message;
    }

    public void SetData(Object data) {
        this.data = data;
    }

    public String toJson() {
        if (data == null)
            data = new Object();
        return new Gson().toJson(this);
    }
}
