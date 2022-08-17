import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.2"
	id("io.spring.dependency-management") version "1.0.12.RELEASE"
	id("org.openapi.generator") version "6.0.1"
	val kotlinVersion = "1.7.10"
	kotlin("plugin.jpa") version kotlinVersion
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
}

group = "nl.codestar"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

val kotlinxCoroutinesVersion = "1.6.4"

repositories {
	mavenCentral()
}

kotlin {
	sourceSets["main"].apply {
		kotlin.srcDir("$buildDir/generated/src/main/kotlin/nl/codestar/petclinic/generated/springkotlin/api")
		kotlin.srcDir("$buildDir/generated/src/main/kotlin/nl/codestar/petclinic/generated/springkotlin/model")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springdoc:springdoc-openapi-webflux-ui:1.6.9")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinxCoroutinesVersion")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$kotlinxCoroutinesVersion")
	implementation("io.swagger.core.v3:swagger-core:2.2.2")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.2")
	implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
	runtimeOnly("javax.annotation:javax.annotation-api:1.3.2")
	runtimeOnly("org.hsqldb:hsqldb")
	runtimeOnly("mysql:mysql-connector-java")
	runtimeOnly("org.postgresql:postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
	dependsOn("openApiGenerate")
}

openApiGenerate {
	generatorName.set("kotlin-spring")
	inputSpec.set("$projectDir/src/main/resources/petclinic.yml")
	outputDir.set("$buildDir/generated")
	validateSpec.set(true)
	packageName.set("nl.codestar.petclinic.generated.springkotlin")
	apiPackage.set("nl.codestar.petclinic.generated.springkotlin.api")
	modelPackage.set("nl.codestar.petclinic.generated.springkotlin.model")
	invokerPackage.set("nl.codestar.petclinic.generated.springkotlin")
	modelNameSuffix.set("Dto")
	configOptions.set(
		mapOf(
			"groupId" to "nl.codestar",
			"delegatePattern" to "true",
			"interfaceOnly" to "false",
			"dateLibrary" to "java8",
			"reactive" to "true",
			"idea" to "true",
			"swaggerAnnotations" to "false"
		)
	)

}

tasks.withType<Test> {
	useJUnitPlatform()
}
