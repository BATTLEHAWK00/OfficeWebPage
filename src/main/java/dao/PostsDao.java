package dao;

import bean.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostsDao extends BaseDao {
    /**
     * ��ȡ���ж�̬
     *
     * @return
     */
    List<Post> getAllPosts() throws SQLException;

    /**
     * ����PID��ȡ����
     *
     * @param pid
     * @return
     */
    Post getPostByPID(String pid) throws SQLException;

    /**
     * ����UID��ȡ����
     *
     * @param uid
     * @return
     */
    List<Post> getPostsByUID(String uid);

    /**
     * ��ȡ����
     *
     * @param onlyPublished �Ƿ�ֻ��ȡ�ѷ���������
     * @return
     */
    List<Post> getPosts(boolean onlyPublished) throws SQLException;
}
