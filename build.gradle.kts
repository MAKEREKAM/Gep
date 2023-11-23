plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.0.0"
}

group = "kr.vanilage"
version = "1.0-Windows"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.json:json:20210307")
}

tasks.test {
    useJUnitPlatform()
}

tasks {
    val shadowJar by getting(com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar::class) {
        manifest {
            attributes["Main-Class"] = "kr.vanilage.Main"
        }
    }
}