package study.spring.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.spring.community.dao.PostDAO;
import study.spring.community.service.PostService;

@Service
public class PostServiceImpl implements PostService {

  private final PostDAO postDAO;

  @Autowired
  public PostServiceImpl(PostDAO postDAO) {
    this.postDAO = postDAO;
  }

}
