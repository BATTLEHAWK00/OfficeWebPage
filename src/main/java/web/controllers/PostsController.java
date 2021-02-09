package web.controllers;

import bean.Response;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PostsService;
import service.impl.PostServiceImpl;

import java.sql.SQLException;

@RestController
@RequestMapping("/post")
public class PostsController {
    PostsService postsService = new PostServiceImpl();

    @RequestMapping("/getlist")
    public String getPosts() throws SQLException {
        return new Gson().toJson(postsService.getPublishedPosts());
    }

    @RequestMapping("/delete")
    public String delPost() {
        return Response.OK.toJsonStr();
    }
}
