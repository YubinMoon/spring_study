package study.spring.community.dao;

import study.spring.community.entity.Post;

public interface PostDAO {

  Post savePost(Post post);

  Post getPost(long postId);

}
