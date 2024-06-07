package coinkiri.core.domain.talk;

import coinkiri.core.domain.coin.Coin;
import coinkiri.core.domain.common.BaseEntity;
import coinkiri.core.domain.member.Member;
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
@Table(name = "talks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Talk extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long talkId;

	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "coin_id")
	private Coin coin;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	public Talk(String content, Coin coin, Member member) {
		this.content = content;
		this.coin = coin;
		this.member = member;
	}
}
