package coinkiri.core.domain.post;

import coinkiri.core.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends Post{

	// 카테고리
	@Column(name = "category", nullable = false)
	private String category;

	@Builder
	public Community(String title, String content, Member member, String category) {
		super(title, content, member);
		this.category = category;
	}
}
