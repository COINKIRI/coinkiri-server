package coinkiri.core.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coinkiri.core.domain.user.User;

/**
 * @ClassName    : $ 클래스에 대한 설명을 작성합니다.
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
