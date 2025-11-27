package study.spring.community.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import study.spring.community.dto.PostCreateRequest;
import study.spring.community.dto.PostDetailResponse;
import study.spring.community.dto.PostIdResponse;
import study.spring.community.service.PostService;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

  private final PostService postService;

  @Autowired
  public PostController(PostService postService) {
    this.postService = postService;
  }

  @PostMapping
  PostIdResponse createPost(@RequestBody PostCreateRequest postCreateRequest) {
    return postService.createPost(postCreateRequest);
  }

  @GetMapping("/{postId}")
  PostDetailResponse getPost(@PathVariable long postId) {
    return postService.getPost(postId);
  }

  @GetMapping
  List<PostDetailResponse> getPosts(@RequestParam int page, @RequestParam int size) {
    return postService.getPosts(page, size);
  }

  @PutMapping("/{postId}")
  PostIdResponse updatePost(@PathVariable int postId, @RequestBody PostCreateRequest postCreateRequest) {
    return postService.updatePost(postId, postCreateRequest);
  }
}
