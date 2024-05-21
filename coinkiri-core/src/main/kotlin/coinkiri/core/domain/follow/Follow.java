package coinkiri.core.domain.follow;

import coinkiri.core.domain.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "follow")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Follow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "follow_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "follower_id", nullable = false)
	private Member follower;

	@ManyToOne
	@JoinColumn(name = "following_id", nullable = false)
	private Member following;

	public Follow(Member follower, Member following) {
		this.follower = follower;
		this.following = following;
	}
}
