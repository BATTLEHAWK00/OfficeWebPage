package dao.impl;

import bean.Post;
import utils.jdbcutils.connection.DBConnector;
import utils.jdbcutils.sql.SQLOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class PostsDao {
    public List<Post> getPosts(boolean onlyPublished) {
        List<Post> postList = new ArrayList<>();
        try {
            DBConnector.get().getConnection(conn -> {
                var query = new SQLOperation(conn);
                String sql = "SELECT * FROM passages";
                query.setSql(sql);
                query.ExecuteQuery(res -> {
                    while (res.next()) {
                        Post post = getPostByResultSet(res);
                        if (onlyPublished) {
                            if (post.getIsPublished().equals("1"))
                                postList.add(post);
                        } else postList.add(post);
                    }
                });
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return postList;
    }

    public List<Post> getPosts() {
        return getPosts(false);
    }

    public Post getPostByPID(String pid) {
        AtomicReference<Post> post = new AtomicReference<>();
        try {
            DBConnector.get().getConnection(conn -> {
                var query = new SQLOperation(conn);
                String sql = String.format("SELECT * FROM passages WHERE PID='%s'", pid);
                query.setSql(sql);
                query.ExecuteQuery(res -> {
                    if (res.next()) {
                        post.set(getPostByResultSet(res));
                    }
                });
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return post.get();
    }

    private Post getPostByResultSet(ResultSet res) throws SQLException {
        Post post = new Post();
        post.setPostID(res.getString("PID"));
        post.setAuthor(new UsersDao().getUserByUID(res.getString("author")));
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
