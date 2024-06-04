package coinkiri.core.domain.coin;

import java.util.ArrayList;
import java.util.List;

import coinkiri.core.domain.talk.Talk;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Coin DB 엔티티
 * - CoinEntity : Coin 테이블의 엔티티 클래스
 */
@Entity
@Table(name = "coins")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Coin {

    // Coin 테이블의 기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coin_id")
    private Long coinId;

    // 마켓명
    @Column(name = "market", nullable = false)
    private String market;

    // 코인 한글명
    @Column(name = "korean_name", nullable = false)
    private String koreanName;

    // 코인 영문명
    @Column(name = "english_name", nullable = false)
    private String englishName;

    // 코인 심볼 이미지
    @Column(name = "symbol_image", columnDefinition = "blob")
    private byte[] symbolImage;

    // 코인 커뮤니티
    @OneToMany(mappedBy = "coin")
    private List<Talk> talks = new ArrayList<>();


    public Coin(String market, String koreanName, String englishName) {
        this.market = market;
        this.koreanName = koreanName;
        this.englishName = englishName;
    }

    public void updateSymbolImage(byte[] symbolImage) {
        this.symbolImage = symbolImage;
    }
}
