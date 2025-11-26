package study.spring.community.service;

import study.spring.community.dto.PostCreateRequest;
import study.spring.community.dto.PostIdResponse;

public interface PostService {

  PostIdResponse createPost(PostCreateRequest postCreateRequest);
}
