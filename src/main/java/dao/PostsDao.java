package dao;

import bean.Post;
import utils.jdbcutils.connection.DBConnector;
import utils.jdbcutils.sql.SQLOperation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostsDao {
    public List<Post> getPosts() {
        List<Post> postList = new ArrayList<>();
        try {
            DBConnector.get().getConnection(conn -> {
                var query = new SQLOperation(conn);
                String sql = "SELECT * FROM passages";
                query.setSql(sql);
                query.ExecuteQuery(res -> {
                    while (res.next()) {
                        postList.add(getPostByResultSet(res));
                    }
                });
            });
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return postList;
    }

    private Post getPostByResultSet(ResultSet res) throws SQLException {
        Post post = new Post();
        post.setPostID(res.getString("passage_id"));
        post.setAuthor(new UsersDao().getUserByUID(res.getString("author")));
        post.setPhoto_src(res.getString("photo_src"));
        post.setPostTitle(res.getString("title"));
        post.setPostContent(res.getString("content"));
        post.setViews(res.getInt("views"));
        post.setPostTime(res.getTimestamp("time").getTime());
        return post;
    }
}
