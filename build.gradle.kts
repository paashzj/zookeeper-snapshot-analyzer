import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("org.jetbrains.compose") version "1.1.1"
}

group = "com.github.shoothzj"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

configurations {
    all {
        // log4j1
        exclude("org.slf4j", "slf4j-log4j12")
        // logback
        exclude("ch.qos.logback", "logback-core")
        exclude("ch.qos.logback", "logback-classic")
    }
}

val junitVersion = "5.9.0"
val log4jVersion = "2.19.0"
val metricsVersion = "4.2.12"
val zooKeeperVersion = "3.8.0"

dependencies {
    implementation(compose.desktop.currentOs)
    implementation("org.apache.zookeeper:zookeeper:$zooKeeperVersion")
    implementation("io.dropwizard.metrics:metrics-core:4.2.12")
    implementation("org.apache.logging.log4j:log4j-core:$log4jVersion")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:$log4jVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks {
    compileJava {
        options.encoding = "UTF-8"
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "zookeeper-snapshot-analyzer"
            packageVersion = "1.0.0"
        }
    }
}
