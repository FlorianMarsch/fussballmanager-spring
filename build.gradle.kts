import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val api by configurations

plugins {
	id("org.springframework.boot") version "2.3.2.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
	kotlin("kapt") version "1.3.72"
}



group = "de.florianmarsch.spring"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8


configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	api("com.querydsl:querydsl-jpa")

	implementation("org.springframework.boot:spring-boot-starter-data-rest")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation ("org.postgresql:postgresql")



	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}


	implementation ("org.zalando:logbook-spring-boot-starter:1.5.0")

	implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.1")


	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")

	kapt("com.querydsl:querydsl-apt:4.2.1:jpa")

}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
