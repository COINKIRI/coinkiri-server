package coinkiri.core.domain.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.post.Post;

public interface PostRepository<T extends Post> extends JpaRepository<T, Long> {


}
