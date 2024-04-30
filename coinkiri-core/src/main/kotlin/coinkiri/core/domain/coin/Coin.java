package coinkiri.core.domain.coin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 *   Coin DB 엔티티
 * - CoinEntity : Coin 테이블의 엔티티 클래스
 */
@Entity
@Table(name = "coins")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coin {

	// coins 테이블 id (인위 식별자)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "coin_id")
	private Long id;

	// 마켓명
	@Column(name = "market", nullable = false)
	private String market;

	// 코인 한글명
	@Column(name = "korean_name", nullable = false)
	private String koreanName;

	// 코인 영문명
	@Column(name = "english_name", nullable = false)
	private String englishName;

	// 코인 심볼 이미지 (blob)
	@Column(name = "symbol_image", columnDefinition = "blob") // byte[]로 저장
	private byte[] symbolImage;

}
