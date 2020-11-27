package service.impl;

import bean.Post;
import dao.PostsDao;
import dao.impl.PostsDaoImpl;
import service.PostsService;

import java.sql.SQLException;
import java.util.List;

/**
 * @class: PostServiceImpl
 * @description:
 * @author: YXL
 **/
public class PostServiceImpl implements PostsService {
    PostsDao postsDao = new PostsDaoImpl();

    @Override
    public List<Post> getAllPosts() {
        try {
            return postsDao.getAllPosts();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Post> getPublishedPosts() {
        try {
            return postsDao.getPosts(true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }
}
