package coinkiri.core.domain.member;

import coinkiri.core.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * @ClassName    : User
 *
 */
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

	// User 테이블의 기본키
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	// 소셜 id
	@Column(name = "social_id", nullable = false)
	private String socialId;

	// 소셜 로그인 타입(KAKAO, NAVER)
	@Column(name = "social_type", nullable = false)
	@Enumerated(EnumType.STRING)
	private SocialType socialType;

	// 프로필 사진
	@Column(name = "pic", columnDefinition = "blob")
	private byte[] pic;

	// 레벨
	@Column(name = "level", nullable = false, columnDefinition = "int default 1")
	private int level;

	// 경험치
	@Column(name = "exp", nullable = false, columnDefinition = "int default 0")
	private int exp;

	// 마일리지
	@Column(name = "mileage", nullable = false, columnDefinition = "int default 0")
	private int mileage;

	public Member(String socialId, String socailType) {
		this.socialId = socialId;
		this.socialType = SocialType.valueOf(socailType);
	}


}
