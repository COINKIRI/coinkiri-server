import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {

    // mysql connector
    runtimeOnly("com.mysql:mysql-connector-j")

    /**
     * spring boot data jpa
     * implementation 대신 api를 사용하면 다른 모듈(api)에서 이 모듈(core)에 의존할 때, 이 모듈이 사용하는 의존성(jpa)을 사용할 수 있다.
     */
    api("org.springframework.boot:spring-boot-starter-data-jpa")
}