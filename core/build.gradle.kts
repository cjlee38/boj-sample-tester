import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.20"
}

group = "io.github.cjlee38"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jsoup:jsoup:1.15.3")

    testImplementation("io.kotest:kotest-runner-junit5:5.4.2")
    implementation ("io.github.microutils:kotlin-logging-jvm:2.0.11")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.register("prepareKotlinBuildScriptModel"){}
