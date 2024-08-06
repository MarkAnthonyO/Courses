import org.jetbrains.kotlin.serialization.builtins.main

plugins {
    id("java")
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

tasks.withType<Copy>() {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

dependencies {
    implementation(files("libs/Courses-API-v1.0.0.jar"))
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.xerial:sqlite-jdbc:3.46.0.0")
}

tasks.withType<Jar>() {
    manifest {
        attributes(Pair("Main-Class", "MainKt"))
    }
}

version = "v1.0.0"