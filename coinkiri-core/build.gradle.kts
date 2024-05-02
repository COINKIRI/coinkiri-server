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

val queryDslVersion = "5.0.0" // QueryDSL Version

dependencies {

    // mysql connector
    runtimeOnly("com.mysql:mysql-connector-j")

    /**
     * spring boot data jpa
     * implementation 대신 api를 사용하면 다른 모듈(api)에서 이 모듈(core)에 의존할 때, 이 모듈이 사용하는 의존성(jpa)을 사용할 수 있다.
     */
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    // QueryDSL
    api("com.querydsl:querydsl-jpa:${queryDslVersion}:jakarta")
    // java annotation processing. 만약 kotlin을 사용한다면 kapt를 사용해야함
    annotationProcessor("com.querydsl:querydsl-apt:${queryDslVersion}:jakarta")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")

}

// QClass 생성 위치 설정
val generatedDir = "src/main/generated/"

tasks.named("clean") { // clean task 실행 시 생성된 QClass 파일 삭제
    doLast {
        file(generatedDir).deleteRecursively()
    }
}
tasks.withType<JavaCompile> { // JavaCompile task 실행 시 QClass 파일 생성 위치 설정
    options.generatedSourceOutputDirectory.set(file(generatedDir))
}