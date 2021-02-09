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
    public List<Post> getAllPosts() throws SQLException {
        return postsDao.getAllPosts();
    }

    @Override
    public List<Post> getPublishedPosts() throws SQLException {
        return postsDao.getPosts(true);
    }
}
