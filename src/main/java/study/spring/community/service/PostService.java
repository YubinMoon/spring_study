package study.spring.community.service;

import java.util.List;

import study.spring.community.dto.PostCreateRequest;
import study.spring.community.dto.PostDetailResponse;
import study.spring.community.dto.PostIdResponse;

public interface PostService {

  PostIdResponse createPost(PostCreateRequest postCreateRequest);

  PostDetailResponse getPost(long postId);

  List<PostDetailResponse> getPosts(int page, int size);

  PostIdResponse updatePost(long postId, PostCreateRequest postCreateRequest);

  void deletePost(long postId);
}
