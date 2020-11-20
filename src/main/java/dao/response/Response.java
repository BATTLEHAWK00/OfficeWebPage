package dao.response;

import com.google.gson.Gson;

public class Response {
    private Object data;
    private String message;

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
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
