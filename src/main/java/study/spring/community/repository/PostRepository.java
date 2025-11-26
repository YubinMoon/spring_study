package study.spring.community.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import study.spring.community.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
