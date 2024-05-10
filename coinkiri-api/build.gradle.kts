import org.springframework.boot.gradle.tasks.bundling.BootJar

val bootJar: BootJar by tasks
bootJar.enabled = true

dependencies {
    implementation(project(":coinkiri-core"))
    implementation(project(":coinkiri-common"))

    // jwt
    implementation("io.jsonwebtoken:jjwt-api:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")

    // security
    implementation("org.springframework.boot:spring-boot-starter-security")
    // oauth2
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
}