package study.spring.community.service.impl;

import study.spring.community.dao.PostDAO;
import study.spring.community.service.PostService;

public class PostServiceImpl implements PostService {

  private final PostDAO postDAO;

  public PostServiceImpl(PostDAO postDAO) {
    this.postDAO = postDAO;
  }

}
