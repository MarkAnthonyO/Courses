plugins {
    id("java")
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
    implementation(files("libs/Courses-API-v0.0.1.jar"))
}

tasks.withType<Jar>() {
    manifest {
        attributes(Pair("Main-Class", "Main.Main"))
    }
}

version = "v0.0.1"