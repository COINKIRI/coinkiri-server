package coinkiri.core.domain.post;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Community extends Post{

	// 카테고리
	@Column(name = "category", nullable = false)
	private String category;
}
