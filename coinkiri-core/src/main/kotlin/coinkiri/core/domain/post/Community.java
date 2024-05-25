package coinkiri.core.domain.post;

import java.util.List;

import coinkiri.core.domain.image.Image;
import coinkiri.core.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Community extends Post {

	// 카테고리
	@Column(name = "category", nullable = true)
	@Enumerated(EnumType.STRING)
	private CategoryType category;

	@Builder
	public Community(String title, String content, Member member, String category) {
		super(title, content, member);
		this.category = CategoryType.valueOf(category);
	}
}

