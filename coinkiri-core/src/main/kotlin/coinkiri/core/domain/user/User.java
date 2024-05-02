package coinkiri.core.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @ClassName    : User
 *
 */
@Entity
@Table(name = "users")
public class User {

	// User 테이블의 기본키
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	// 소셜 id
	@Column(name = "social_id", nullable = false)
	private String socialId;

	// 소셜 로그인 타입(KAKAO, NAVER)
	@Column(name = "social_type", nullable = false)
	private SocialType socialType;

	// // 프로필 사진
	// @Column(name = "pic", columnDefinition = "blob")
	// private byte[] pic;
	//
	// // 레벨
	// @Column(name = "level", nullable = false, columnDefinition = "int default 1")
	// private int level;
	//
	// // 경험치
	// @Column(name = "exp", nullable = false, columnDefinition = "int default 0")
	// private int exp;
	//
	// // 마일리지
	// @Column(name = "mileage", nullable = false, columnDefinition = "int default 0")
	// private int mileage;


}
