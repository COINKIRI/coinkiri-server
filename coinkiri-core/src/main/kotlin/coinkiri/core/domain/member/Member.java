package coinkiri.core.domain.member;

import java.io.IOException;
import java.nio.file.Files;

import org.springframework.util.ResourceUtils;

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
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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

	// 닉네임
	@Column(name = "nickname", nullable = false)
	private String nickname;

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

	// 자기소개
	@Column(name = "status_message", nullable = false, columnDefinition = "varchar(255) default '자기소개가 없습니다'")
	private String statusMessage;

	public Member(String socialId, String socailType) {
		this.socialId = socialId;
		this.socialType = SocialType.valueOf(socailType);
		this.nickname = generateNickname();
		this.pic = getDefaultProfileImage();
		this.level = 1;
		this.exp = 0;
		this.mileage = 0;
		this.statusMessage = "자기소개가 없습니다";
	}

	// 랜덤 닉네임 생성 (social id 통해 만들도록 수정 예정)
	private String generateNickname() {
		return "user_" + (int) (Math.random() * 10000000);
	}

	// 닉네임 업데이트
	public void updateNickname(String nickname) {
		this.nickname = nickname;
	}

	// 상태 메시지 업데이트
	public void updateStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	// 유저 정보 수정
	public void updateInfo(String nickname, String statusMessage) {
		this.nickname = nickname;
		this.statusMessage = statusMessage;
	}

	// 프로필 사진 업데이트
	public void updateProfileImage(byte[] pic) {
		this.pic = pic;
	}

	// 기본 프로필 사진 초기화
	private byte[] getDefaultProfileImage() {
		try {
			return Files.readAllBytes(ResourceUtils.getFile("classpath:profileImage/defaultProfile.png").toPath());
		} catch (IOException e) {
			throw new RuntimeException("Default profile image could not be loaded", e);
		}
	}

}
