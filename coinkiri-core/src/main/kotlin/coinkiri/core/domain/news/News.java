package coinkiri.core.domain.news;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class News {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "news_id")
	private Long id;

	@Column(name = "title", nullable = false)
	private String title;

	@Column(name = "link", nullable = false)
	private String link;

	@Column(name = "description", nullable = false, columnDefinition = "text")
	private String description;

	@Column(name = "pub_date", nullable = false)
	private LocalDateTime pubDate;


}
