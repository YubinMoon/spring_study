package study.spring.community.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import study.spring.community.dao.PostDAO;
import study.spring.community.dto.PostCreateRequest;
import study.spring.community.dto.PostDetailResponse;
import study.spring.community.dto.PostIdResponse;
import study.spring.community.entity.Post;
import study.spring.community.service.PostService;

@Service
public class PostServiceImpl implements PostService {

  private final PostDAO postDAO;

  @Autowired
  public PostServiceImpl(PostDAO postDAO) {
    this.postDAO = postDAO;
  }

  @Override
  public PostIdResponse createPost(PostCreateRequest postCreateRequest) {
    Post post = new Post();
    post.setTitle(postCreateRequest.getTitle());
    post.setContent(postCreateRequest.getContent());
    Post savedPost = postDAO.savePost(post);
    return new PostIdResponse(savedPost.getId());
  }

  @Override
  public PostDetailResponse getPost(long postId) {
    Post post = postDAO.getPost(postId);
    return PostDetailResponse.builder()
        .postId(post.getId())
        .title(post.getTitle())
        .content(post.getContent())
        .createdAt(post.getCreatedAt())
        .likeCount(0)
        .build();
  }

}
