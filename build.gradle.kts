import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.2"
	id("io.spring.dependency-management") version "1.0.12.RELEASE"
	id("org.openapi.generator") version "6.0.1"
	kotlin("jvm") version "1.7.10"
	kotlin("plugin.spring") version "1.7.10"
}

group = "nl.codestar"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

kotlin {
	sourceSets["main"].apply {
		kotlin.srcDir("$buildDir/generated/src/main/kotlin/nl/codestar/springpetclinickotlin/api")
		kotlin.srcDir("$buildDir/generated/src/main/kotlin/nl/codestar/springpetclinickotlin/model")
	}
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.swagger.core.v3:swagger-core:2.2.2")
	implementation("io.swagger.core.v3:swagger-annotations:2.2.2")
	implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")
	runtimeOnly("javax.annotation:javax.annotation-api:1.3.2")
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
	inputSpec.set("$projectDir/src/main/resources/petstore.yml")
	outputDir.set("$buildDir/generated")
	validateSpec.set(true)
	packageName.set("nl.codestar.springpetclinickotlin")
	apiPackage.set("nl.codestar.springpetclinickotlin.api")
	modelPackage.set("nl.codestar.springpetclinickotlin.model")
	invokerPackage.set("nl.codestar.springpetclinickotlin")
	modelNameSuffix.set("Dto")
	configOptions.set(
		mapOf(
			"groupId" to "nl.codestar",
			"delegatePattern" to "true",
			"interfaceOnly" to "false",
			"dateLibrary" to "java8",
			"idea" to "true",
			"swaggerAnnotations" to "false"
		)
	)

}

tasks.withType<Test> {
	useJUnitPlatform()
}
