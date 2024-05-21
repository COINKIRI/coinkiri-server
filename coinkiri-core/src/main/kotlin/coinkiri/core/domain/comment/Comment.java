package coinkiri.core.domain.comment;

import coinkiri.core.domain.common.BaseEntity;
import coinkiri.core.domain.member.Member;
import coinkiri.core.domain.post.Post;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Comment extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id")
	private Post post;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "parent_id")
	// private Comment parentComment;

	@Column(name = "content", nullable = false)
	private String content;

	public Comment(Post post, Member member,
		// Comment parentComment,
		String content
	) {
		this.post = post;
		this.member = member;
		// this.parentComment = parentComment;
		this.content = content;
	}

}
