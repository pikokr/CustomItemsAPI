import org.gradle.kotlin.dsl.execution.ProgramText

plugins {
    kotlin("jvm") version "1.3.72"
    id("com.github.johnrengelman.shadow") version "5.2.0"
    maven
    `maven-publish`
}

group = "com.github.parangee"
version = "0.1.1"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("com.destroystokyo.paper:paper-api:1.16.1-R0.1-SNAPSHOT")
    implementation("com.github.noonmaru:kommand:0.1.9")
    implementation("com.github.parangee:chat-builder-api:0.0.4")
}


tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    processResources {
        filesMatching("**/*.yml") {
            expand(project.properties)
        }
    }

    shadowJar {
        archiveClassifier.set("lib")
    }
    artifacts {
        add("archives", shadowJar)
    }
}