package service;

import bean.Post;

import java.sql.SQLException;
import java.util.List;

public interface PostsService {
    /**
     * ��ȡ���ж�̬����
     *
     * @return
     */
    List<Post> getAllPosts() throws SQLException;

    /**
     * ��ȡ�ѷ�������
     *
     * @return
     */
    List<Post> getPublishedPosts() throws SQLException;

}
