import org.gradle.jvm.tasks.Jar

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

/*tasks.jar {
    manifest.attributes["Main-Class"] = "com.gearsofleo.cloudrun.MainKt"
    val dependencies = configurations
        .runtimeClasspath
        .get()
        .map(::zipTree) // OR .map { zipTree(it) }

    from(dependencies)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    //excludes = ['META-INF/*.RSA','META-INF/*.SF','META-INF/*.DSA']
}*/

// Shadow task depends on Jar task, so these configs are reflected for Shadow as well
/*tasks.jar {
    manifest.attributes["Main-Class"] = "com.gearsofleo.cloudrun.MainKt"
}
*/
/*tasks {
    "build" {
        dependsOn(fatJar)
    }
}*/
