package study.spring.community.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {

  @NotNull
  private String username;
  @NotNull
  @Min(8)
  private String password;
  @NotNull
  @Min(2)
  @Max(10)
  private String nickname;
}
