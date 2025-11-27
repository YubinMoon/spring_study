package study.spring.community.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import study.spring.community.dao.PostDAO;
import study.spring.community.entity.Post;
import study.spring.community.repository.PostRepository;

@Component
public class PostDAOImpl implements PostDAO {

  private final PostRepository postRepository;

  @Autowired
  public PostDAOImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

  @Override
  public Post savePost(Post post) {
    return postRepository.save(post);
  }

  @Override
  public Post getPost(long postId) {
    return postRepository.findById(postId)
        .orElseThrow(() -> new RuntimeException("Post not found"));
  }

  @Override
  public List<Post> getPosts(int page, int size) {
    return postRepository.findAllByOrderByCreatedAtDesc(PageRequest.of(page, size));
  }

  @Override
  public void deletePost(long postId) {
    postRepository.deleteById(postId);
  }

}
