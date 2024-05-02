import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = false

dependencies {
    // Spring Boot Starter Web
    api("org.springframework.boot:spring-boot-starter-web")
}