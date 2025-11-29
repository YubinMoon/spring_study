package study.spring.community.service;

import study.spring.community.dto.UserIdResponse;
import study.spring.community.dto.UserRegisterRequest;

public interface UserService {

  UserIdResponse register(UserRegisterRequest userRegisterRequest);

}
