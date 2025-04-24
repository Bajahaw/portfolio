plugins {
	java
	id("org.springframework.boot") version "3.4.1"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.graalvm.buildtools.native") version "0.10.4"
	id("gg.jte.gradle") version "3.1.12"
}

group = "tech.radhi"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

graalvmNative {
	binaries {
		named("main") {
			buildArgs.add("--libc=musl")
			buildArgs.add("--static")
		}
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("gg.jte:jte:3.1.12")
	implementation("gg.jte:jte-spring-boot-starter-3:3.1.12")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.6")
	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	runtimeOnly("org.postgresql:postgresql")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

jte {
	generate()
	binaryStaticContent = true
}

tasks.withType<Test> {
	useJUnitPlatform()
}
