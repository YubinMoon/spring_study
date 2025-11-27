package study.spring.community.dao;

import java.util.List;

import study.spring.community.entity.Post;

public interface PostDAO {

  Post savePost(Post post);

  Post getPost(long postId);

  List<Post> getPosts(int page, int size);

  void deletePost(long postId);

}
