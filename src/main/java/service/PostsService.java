package service;

import bean.Post;

import java.util.List;

public interface PostsService {
    /**
     * 获取所有动态文章
     *
     * @return
     */
    List<Post> getAllPosts();

    /**
     * 获取已发布文章
     *
     * @return
     */
    List<Post> getPublishedPosts();

}
