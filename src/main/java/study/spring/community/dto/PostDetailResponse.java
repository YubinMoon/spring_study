package study.spring.community.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailResponse {

  private Long postId;
  private String title;
  private String content;
  // private UserInfoResponse author;
  private LocalDateTime createdAt;
  private int likeCount;
  // private int unlikeCount;
  // private CommentResponse[] comments;
}
