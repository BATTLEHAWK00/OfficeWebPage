package service;

import bean.Post;

import java.util.List;

public interface PostsService {
    /**
     * ��ȡ���ж�̬����
     *
     * @return
     */
    List<Post> getAllPosts();

    /**
     * ��ȡ�ѷ�������
     *
     * @return
     */
    List<Post> getPublishedPosts();

}
