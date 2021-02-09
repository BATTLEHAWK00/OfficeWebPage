package bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import lombok.Data;

/**
 * ÏìÓ¦Êý¾Ý
 */
public class Response {
    private Object data;
    private String message;

    public static final Response OK = new Response("OK");
    public static final Response Fail = new Response("Fail");

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

    public String toJsonStr() {
        if (data == null)
            data = new Object();
        return new Gson().toJson(this);
    }
}
