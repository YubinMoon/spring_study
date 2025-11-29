package study.spring.community.dao.impl;

import org.springframework.stereotype.Component;

import study.spring.community.dao.UserDAO;
import study.spring.community.entity.User;
import study.spring.community.repository.UserRepository;

@Component
public class UserDAOImpl implements UserDAO {

  private final UserRepository userRepository;

  public UserDAOImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User saveUser(User user) {
    return userRepository.save(user);
  }

}
