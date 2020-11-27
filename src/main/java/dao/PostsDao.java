package dao;

import bean.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostsDao extends BaseDao {
    /**
     * 获取所有动态
     *
     * @return
     */
    List<Post> getAllPosts() throws SQLException;

    /**
     * 根据PID获取文章
     *
     * @param pid
     * @return
     */
    Post getPostByPID(String pid) throws SQLException;

    /**
     * 根据UID获取文章
     *
     * @param uid
     * @return
     */
    List<Post> getPostsByUID(String uid);

    /**
     * 获取文章
     *
     * @param onlyPublished 是否只获取已发布的文章
     * @return
     */
    List<Post> getPosts(boolean onlyPublished) throws SQLException;
}
