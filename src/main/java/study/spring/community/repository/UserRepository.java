package study.spring.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.spring.community.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
