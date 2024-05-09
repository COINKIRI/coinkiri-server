package coinkiri.core.config.jpa

import coinkiri.core.CoinkiriCoreRoot
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

/**
 * @ClassName    : JpaConfig
 * core 모듈의 의존성을 api 모듈에 추가하는 것과 별개로 Bean 등록은 따로 해줘야함.
 * Core 모듈의 모든 Entity와 Repository를 스캔하기 위한 설정
 */
@Configuration
@EntityScan(basePackageClasses = [CoinkiriCoreRoot::class])
@EnableJpaRepositories(basePackageClasses = [CoinkiriCoreRoot::class])
@EnableJpaAuditing
internal class JpaConfig