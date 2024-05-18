package coinkiri.api.config.resolver

import coinkiri.api.config.interceptor.Auth
import coinkiri.api.service.auth.jwt.JwtKey
import coinkiri.common.exception.CoinkiriException
import coinkiri.common.exception.ExceptionCode
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer


@Component
class MemberIdResolver : HandlerMethodArgumentResolver {
    
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(MemberID::class.java) && Long::class.java == parameter.parameterType
    }

    override fun resolveArgument(
        parameter: MethodParameter, mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?
    ): Any? {
        parameter.getMethodAnnotation(Auth::class.java)
            ?: throw CoinkiriException(
                ExceptionCode.INTERNAL_SERVER_EXCEPTION
            )

        return webRequest.getAttribute(JwtKey.MEMBER_ID, 0)
            ?: throw CoinkiriException(
                ExceptionCode.INTERNAL_SERVER_EXCEPTION2,
            )
    }
}
