package coinkiri.api.config.swagger

import coinkiri.api.config.resolver.MemberID
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springdoc.core.utils.SpringDocUtils
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    companion object {
        private const val TITLE = "Coinkiri API Server"
        private const val DESCRIPTION = "Coinkiri API Docs"
        private const val VERSION = "1.0.0"
    }

    @Bean
    fun openAPI() : OpenAPI {
        val info = Info()
            .title(TITLE)
            .description(DESCRIPTION)
            .version(VERSION)

        val securityScheme = SecurityScheme()
            .type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
            .`in`(SecurityScheme.In.HEADER).name("Authorization")
        val securityRequirement = SecurityRequirement().addList("Bearer Token")

        return OpenAPI()
            .components(Components().addSecuritySchemes("Bearer Token", securityScheme))
            .security(listOf(securityRequirement))
            .info(info)
    }

    init {
        SpringDocUtils.getConfig().addAnnotationsToIgnore(MemberID::class.java)
    }

}