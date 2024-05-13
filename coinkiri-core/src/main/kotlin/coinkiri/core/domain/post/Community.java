package coinkiri.core.domain.post;

import coinkiri.core.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("C")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Community extends Post{

	// 카테고리
	@Column(name = "category", nullable = false)
	private String category;

	public Community(String title, String content, Member member, String category) {
		super(title, content, member);
		this.category = category;
	}
}
