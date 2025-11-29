package study.spring.community.service.impl;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import study.spring.community.dao.UserDAO;
import study.spring.community.dto.UserIdResponse;
import study.spring.community.dto.UserLoginRequest;
import study.spring.community.dto.UserRegisterRequest;
import study.spring.community.entity.User;
import study.spring.community.service.UserService;
import study.spring.community.utils.PasswordEncoder;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

  private final UserDAO userDAO;

  public UserServiceImpl(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  @Override
  public UserIdResponse register(UserRegisterRequest userRegisterRequest) {
    String hashedPassword = PasswordEncoder.hashpw(userRegisterRequest.getPassword());

    User user = new User();
    user.setUsername(userRegisterRequest.getUsername());
    user.setPasswordHash(hashedPassword);
    user.setDeleted(false);
    User savedUser = userDAO.saveUser(user);
    return new UserIdResponse(savedUser.getId());
  }

  @Override
  public UserIdResponse login(UserLoginRequest userLoginRequest) {
    User user = userDAO.getUser(userLoginRequest.getUsername());
    if (user == null) {
      throw new IllegalArgumentException("Invalid username or password");
    }

    boolean passwordMatches = PasswordEncoder.checkpw(
        userLoginRequest.getPassword(),
        user.getPasswordHash());
    if (!passwordMatches) {
      throw new IllegalArgumentException("Invalid username or password");
    }

    return new UserIdResponse(user.getId());
  }

}
