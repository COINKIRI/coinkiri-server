package coinkiri.core.domain.post;

import java.util.ArrayList;
import java.util.List;

import coinkiri.core.domain.comment.Comment;
import coinkiri.core.domain.common.BaseEntity;
import coinkiri.core.domain.image.Image;
import coinkiri.core.domain.member.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "posts")
@Inheritance(strategy = InheritanceType.JOINED) // JOINED 전략 사용
@DiscriminatorColumn(name = "DTYPE") // DTYPE 컬럼으로 구분
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Post extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "content", nullable = false, columnDefinition = "text")
	private String content;

	@Column(name = "view_cnt", nullable = false, columnDefinition = "int default 0")
	private int viewCnt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "post")
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Image> images = new ArrayList<>();

	public Post(String title, String content, Member member) {
		this.title = title;
		this.content = content;
		this.member = member;
	}

	// 조회수 증가
	public void increaseViewCnt() {
		this.viewCnt++;
	}

	// 이미지 추가
	public void addImage(Image images) {
		this.images.add(images);
	}
}
