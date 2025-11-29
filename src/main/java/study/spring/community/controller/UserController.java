package study.spring.community.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import study.spring.community.dto.UserIdResponse;
import study.spring.community.dto.UserRegisterRequest;
import study.spring.community.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@RestController("/api/v1/users")
public class UserController {

  private final UserService userService; 

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserIdResponse> register(
      @RequestBody UserRegisterRequest userRegisterRequest,
      HttpServletRequest request) {
    UserIdResponse userIdResponse = userService.register(userRegisterRequest);
    HttpSession session = request.getSession();
    log.info("Session Id: {}", session.getAttribute("userId"));
    session.setAttribute("userId", userRegisterRequest.getUsername());
    log.info("Session Id: {}", session.getId());
    log.info("Session Id: {}", session.getAttribute("userId"));
    ResponseEntity<UserIdResponse> responseEntity = ResponseEntity.ok(userIdResponse);
    return responseEntity;
  }
}
