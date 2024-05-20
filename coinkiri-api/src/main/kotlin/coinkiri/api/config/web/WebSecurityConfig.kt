package coinkiri.api.config.web

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configurable
@Configuration
@EnableWebSecurity
class WebSecurityConfig {
    @Bean
    protected fun configure(httpSecurity: HttpSecurity): SecurityFilterChain {

        httpSecurity
            .cors {
                it.configurationSource(corsConfigurationSource()) // cors 설정
            }
            .csrf {
                it.disable() // csrf 비활성화
            }
            .httpBasic {
                it.disable() // httpBasic 비활성화 (Bearer 방식 사용할 것이기 때문)
            }
            .sessionManagement() {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 비활성화
            }
            .authorizeHttpRequests() {
                it.requestMatchers("/", "/api/v1/auth/**", "/api/v1/batch/**",
                    "/api/v1/coin/**", "/api/v1/member/**",
                    "/api/v1/post/**", "/api/v1/comment/**",
                    "/swagger-ui/**", "/v3/**" // swagger 경로 허용
                )
                    .permitAll() // 허용할 경로
                    .anyRequest().authenticated() // 나머지는 인증 필요
            }
            .exceptionHandling() {
                it.authenticationEntryPoint(FailedAuthenticationEntryPoint()) // 인증 실패 시 처리
            }
//                .addFilterBefore(JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java) // JWT 필터 추가


        return httpSecurity.build();
    }

    // cors 설정
    @Bean
    protected fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedOrigin("*") // 모든 origin 허용
        corsConfiguration.addAllowedMethod("*") // 모든 HTTP method 허용
        corsConfiguration.addAllowedHeader("*") // 모든 HTTP header 허용

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfiguration) // 모든 경로에 대해 corsConfiguration 적용

        return source
    }

}

// 인증 실패 시 처리
class FailedAuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.contentType = "application/json"
        response.status = HttpServletResponse.SC_UNAUTHORIZED
        response.writer.write("{\"code\": \"NP\", \"message\": \"No Permission.\"}")

    }
}


