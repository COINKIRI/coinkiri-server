package coinkiri.api.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
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


        return OpenAPI()
            .info(info)
    }

}