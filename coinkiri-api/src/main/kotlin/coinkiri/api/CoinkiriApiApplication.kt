package coinkiri.api

import coinkiri.core.CoinkiriCoreRoot
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackageClasses = [ // scanBasePackageClasses를 사용하여 해당 클래스가 속한 패키지를 기준으로 컴포넌트 스캔이 이뤄짐
    CoinkiriApiApplication::class,
    CoinkiriCoreRoot::class
])
class CoinkiriApiApplication

fun main(args: Array<String>) {
    runApplication<CoinkiriApiApplication>(*args)
}