import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

// Java 컴파일러의 annotation processing이 Kotlin에서 적용되도록 설정
kapt {
    keepJavacAnnotationProcessors = true
}

sourceSets {
    // Kotlin 모듈에서 Java 코드를 사용할 수 있도록 설정
    main {
        java.srcDirs("src/main/kotlin")
    }
}

dependencies {

    // mysql connector
    runtimeOnly("com.mysql:mysql-connector-j")

    /**
     * spring boot data jpa
     * implementation 대신 api를 사용하면 다른 모듈(api)에서 이 모듈(core)에 의존할 때, 이 모듈이 사용하는 의존성(jpa)을 사용할 수 있다.
     */
    api("org.springframework.boot:spring-boot-starter-data-jpa")


}