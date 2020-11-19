package dao.response;

import com.google.gson.Gson;

public class Response {
    private Data data;
    private String message;

    public void SetData(Data data) {
        this.data = data;
    }

    public void SetMessage(String message) {
        this.message = message;
    }

    public Response() {

    }

    public Response(String message) {
        this.message = message;
        this.data = new Data();
    }

    public Response(String message, Data data) {
        this.message = message;
        this.data = data;
    }

    public String toJson() {
        if (data == null)
            data = new Data();
        return new Gson().toJson(this);
    }
}
