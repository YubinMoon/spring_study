package study.spring.community.dao.impl;

import study.spring.community.dao.PostDAO;
import study.spring.community.repository.PostRepository;

public class PostDAOImpl implements PostDAO {

  private final PostRepository postRepository;

  public PostDAOImpl(PostRepository postRepository) {
    this.postRepository = postRepository;
  }

}
