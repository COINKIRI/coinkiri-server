package coinkiri.api.config

import coinkiri.api.service.auth.JwtAuthenticationFilter
import coinkiri.api.service.auth.OAuth2SuccessHandler
import coinkiri.api.service.auth.OAuth2UserServiceImpl
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
class WebSecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val oAuth2UserServiceImpl: OAuth2UserServiceImpl,
    private val oAuth2SuccessHandler: OAuth2SuccessHandler
) {
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
                it.requestMatchers("/", "/api/v1/auth/**", "/oauth2/**",
                    "/api/v1/coin/**", "/api/v1/member/**",
                    "/api/v1/post/**", "/api/v1/comment/**",
                    "/swagger-ui/**", "/v3/**" // swagger 경로 허용
                )
                    .permitAll() // 허용할 경로
                    .anyRequest().authenticated() // 나머지는 인증 필요
            }
            .oauth2Login() { oauth2 ->
                oauth2 // oauth2 로그인 설정
                    .authorizationEndpoint {
                        it.baseUri("/api/v1/auth/oauth2") // oauth2 로그인 시 api 경로
                    }
                    .redirectionEndpoint {
                        it.baseUri("/oauth2/callback/*") // oauth2 로그인 시 리다이렉션 경로
                    }
                    .userInfoEndpoint {
                        it.userService(oAuth2UserServiceImpl) // oauth2 로그인 시 사용할 서비스
                    }
                    .successHandler(oAuth2SuccessHandler) // oauth2 로그인 성공 시 핸들러
            }.exceptionHandling() {
                it.authenticationEntryPoint(FailedAuthenticationEntryPoint()) // 인증 실패 시 처리
            }.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java) // 필터 등록

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


