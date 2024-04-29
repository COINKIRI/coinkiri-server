import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {

    // mysql connector
    runtimeOnly("com.mysql:mysql-connector-j")

    // spring boot data jpa
    /**
     * @Issue implemetation 대신 api로 변경해야 할 수 있음
     */
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}