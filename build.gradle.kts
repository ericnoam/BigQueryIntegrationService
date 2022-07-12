plugins {
    application
    kotlin("jvm") version "1.7.0"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "com.gearsofleo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("com.google.cloud:google-cloud-bigquery:2.13.8")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("com.google.truth:truth:1.1.3")
    testImplementation("com.google.cloud:google-cloud-logging:3.9.0")
}

application {
    mainClass.set("com.gearsofleo.cloudrun.MainKt")
}
