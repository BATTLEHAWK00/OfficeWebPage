package dao.impl;

import bean.Post;
import dao.PostsDao;
import utils.jdbcutils.connection.DBConnector;
import utils.jdbcutils.sql.SQLOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PostsDaoImpl implements PostsDao {
    @Override
    public List<Post> getPosts(boolean onlyPublished) throws SQLException {
        List<Post> postList = new ArrayList<>();
        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            String sql = "SELECT * FROM passages";
            query.setSql(sql);
            query.ExecuteQuery(res -> {
                while (res.next()) {
                    Post post = getByResultSet(res);
                    if (onlyPublished) {
                        if (post.getIsPublished().equals("1"))
                            postList.add(post);
                    } else postList.add(post);
                }
            });
        });
        return postList;
    }

    @Override
    public Post getPostByPID(String pid) throws SQLException {
        AtomicReference<Post> post = new AtomicReference<>();
        DBConnector.get().getConnection(conn -> {
            var query = new SQLOperation(conn);
            String sql = String.format("SELECT * FROM passages WHERE PID='%s'", pid);
            query.setSql(sql);
            query.ExecuteQuery(res -> {
                if (res.next()) {
                    post.set(getByResultSet(res));
                }
            });
        });
        return post.get();
    }

    @Override
    public List<Post> getPostsByUID(String uid) {
        return null;
    }


    @Override
    public List<Post> getAllPosts() throws SQLException {
        return getPosts(false);
    }

    @Override
    public Post getByResultSet(ResultSet res) throws SQLException {
        Post post = new Post();
        post.setPostID(res.getString("PID"));
        post.setAuthor(new UsersDaoImpl().getUserByUID(res.getString("author")));
        post.setPhoto_src(res.getString("photo_src"));
        post.setPostTitle(res.getString("title"));
        post.setPostContent(res.getString("content"));
        post.setViews(res.getInt("views"));
        post.setCategory(res.getString("category"));
        post.setPostTime(res.getTimestamp("pub_time").getTime());
        post.setIsPublished(res.getString("isPublished"));
        return post;
    }
}
