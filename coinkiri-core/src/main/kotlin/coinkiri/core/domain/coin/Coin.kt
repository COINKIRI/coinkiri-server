package coinkiri.core.domain.coin

import jakarta.persistence.*
import lombok.AccessLevel
import lombok.NoArgsConstructor

/**
 * Coin DB 엔티티
 * - CoinEntity : Coin 테이블의 엔티티 클래스
 */
@Entity
@Table(name = "coins")
class Coin (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coin_id")
    var id: Long? = null,

    @Column(name = "market", nullable = false)
    var market: String,

    @Column(name = "korean_name", nullable = false)
    var koreanName: String,

    @Column(name = "english_name", nullable = false)
    var englishName: String,

    @Column(name = "symbol_image", columnDefinition = "blob")
    var symbolImage: ByteArray

) {

}
